provider "aws" {
  region = var.region
}

#Create the VPC
resource "aws_vpc" "SEP4" {
  # Creating VPC here
  cidr_block       = var.main_vpc_cidr     # Defining the CIDR block use 10.0.0.0/24 for demo
  instance_tenancy = "default"
  enable_dns_support = true
  enable_dns_hostnames = true
  tags             = {
    Name = "sep4"
  }
}
#Create Internet Gateway and attach it to VPC
resource "aws_internet_gateway" "IGW" {
  # Creating Internet Gateway
  vpc_id = aws_vpc.SEP4.id               # vpc_id will be generated after we create VPC
}

#Create a Public Subnets.
resource "aws_subnet" "publicsubnet1" {
  # Creating Public Subnets
  vpc_id            = aws_vpc.SEP4.id
  cidr_block        = var.public_subnet1_cidr        # CIDR block of public subnets
  availability_zone = "eu-central-1a"
  tags              = {
    Name = "sep4-public1"
  }
}

#Create a Public Subnets.
resource "aws_subnet" "publicsubnet2" {
  # Creating Public Subnets
  vpc_id            = aws_vpc.SEP4.id
  cidr_block        = var.public_subnet2_cidr        # CIDR block of public subnets
  availability_zone = "eu-central-1b"
  tags              = {
    Name = "sep4-public2"
  }
}

#Create a Private Subnet                   # Creating Private Subnets
resource "aws_subnet" "privatesubnets" {
  vpc_id            = aws_vpc.SEP4.id
  cidr_block        = var.private_subnet_cidr          # CIDR block of private subnets
  availability_zone = "eu-central-1a"
  tags              = {
    Name = "sep4-private1"
  }
}

#Route table for Public Subnet's
resource "aws_route_table" "PublicRT" {
  # Creating RT for Public Subnet
  vpc_id = aws_vpc.SEP4.id
  route {
    cidr_block = "0.0.0.0/0"               # Traffic from Public Subnet reaches Internet via Internet Gateway
    gateway_id = aws_internet_gateway.IGW.id
  }
}

#Route table for Private Subnet 's
resource "aws_route_table" "PrivateRT" {
  # Creating RT for Private Subnet
  vpc_id = aws_vpc.SEP4.id
  route {
    cidr_block     = "0.0.0.0/0"             # Traffic from Private Subnet reaches Internet via NAT Gateway
    nat_gateway_id = aws_nat_gateway.NATgw.id
  }
}

#Route table Association with Public Subnet 's
resource "aws_route_table_association" "PublicRTassociation1" {
  subnet_id      = aws_subnet.publicsubnet1.id
  route_table_id = aws_route_table.PublicRT.id
}

#Route table Association with Public Subnet 's
resource "aws_route_table_association" "PublicRTassociation2" {
  subnet_id      = aws_subnet.publicsubnet2.id
  route_table_id = aws_route_table.PublicRT.id
}

#Route table Association with Private Subnet 's
resource "aws_route_table_association" "PrivateRTassociation" {
  subnet_id      = aws_subnet.privatesubnets.id
  route_table_id = aws_route_table.PrivateRT.id
}

resource "aws_eip" "nateIP" {
  vpc = true
}

#Creating the NAT Gateway using subnet_id and allocation_id
resource "aws_nat_gateway" "NATgw" {
  allocation_id = aws_eip.nateIP.id
  subnet_id     = aws_subnet.publicsubnet1.id
}

resource "aws_db_subnet_group" "sep4_subnet_group" {
  name       = "sep4_public_subnet_group"
  subnet_ids = [aws_subnet.publicsubnet1.id, aws_subnet.publicsubnet2.id]
  tags       = {
    Name = "sep4_public"
  }
}
