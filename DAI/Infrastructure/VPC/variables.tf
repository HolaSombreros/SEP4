variable "region" {
  default = "eu-central-1"
}

variable "main_vpc_cidr" {
  default = "10.0.0.0/24"
}

variable "public_subnet1_cidr" {
  default = "10.0.0.192/27"
}

variable "public_subnet2_cidr" {
  default = "10.0.0.224/27"
}

variable "private_subnet_cidr" {
  default = "10.0.0.128/26"
}