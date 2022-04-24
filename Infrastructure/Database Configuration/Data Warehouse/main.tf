provider "aws" {
  region = var.region
}

terraform {
  backend "s3" {
    bucket = "sep4-terraform-state"
    region = "eu-central-1"
    dynamodb_table = "sep4-terraform-state-table"
    key = "data-warehouse"
  }
}

# ---------------------------------------------------------------------------------------------------------------------
# CREATE A SECURITY GROUP TO ALLOW ACCESS TO THE RDS INSTANCE
# ---------------------------------------------------------------------------------------------------------------------

resource "aws_security_group" "sep4_data_warehouse_security_group" {
  name   = var.name
  vpc_id = var.vpc_id
}

resource "aws_security_group_rule" "allow_db_access" {
  type              = "ingress"
  from_port         = var.port
  to_port           = var.port
  protocol          = "tcp"
  security_group_id = aws_security_group.sep4_data_warehouse_security_group.id
  cidr_blocks       = ["0.0.0.0/0"]
}

# ---------------------------------------------------------------------------------------------------------------------
# CREATE THE DATABASE INSTANCE
# ---------------------------------------------------------------------------------------------------------------------

resource "aws_db_instance" "sep4_data_warehouse" {
  identifier             = var.name
  engine                 = var.engine_name
  engine_version         = var.engine_version
  port                   = var.port
  name                   = ""
  username               = var.username
  password               = var.password
  instance_class         = var.instance_class
  allocated_storage      = var.allocated_storage
  skip_final_snapshot    = true
  license_model          = var.license_model
  db_subnet_group_name   = var.subnet_group_id
  vpc_security_group_ids = [aws_security_group.sep4_data_warehouse_security_group.id]
  publicly_accessible    = true
  parameter_group_name   = "default.sqlserver-ex-14.0"

  tags = {
    Name = var.name
  }
}

