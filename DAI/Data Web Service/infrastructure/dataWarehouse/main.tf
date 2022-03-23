provider "aws" {
  region = var.region
}

resource "aws_db_option_group" "sep4_data_warehouse_option_group" {
  name                 = var.name
  engine_name          = var.engine_name
  major_engine_version = var.major_engine_version

  tags = {
    Name = var.name
  }

  option {
    option_name = "MARIADB_AUDIT_PLUGIN"

    option_settings {
      name  = "SERVER_AUDIT_EVENTS"
      value = "CONNECT"
    }
  }
}

resource "aws_db_parameter_group" "sep4_data_warehouse_parameter_group" {
  name   = var.name
  family = var.family

  tags = {
    Name = var.name
  }

  parameter {
    name  = "general_log"
    value = "0"
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
  name                   = var.database_name
  username               = var.username
  password               = var.password
  instance_class         = var.instance_class
  allocated_storage      = var.allocated_storage
  skip_final_snapshot    = true
  license_model          = var.license_model
  db_subnet_group_name   = var.subnet_group_id
  vpc_security_group_ids = [aws_security_group.sep4_data_warehouse_security_group.id]
  publicly_accessible    = true
  parameter_group_name   = aws_db_parameter_group.sep4_data_warehouse_parameter_group.id
  option_group_name      = aws_db_option_group.sep4_data_warehouse_option_group.id

  tags = {
    Name = var.name
  }
}