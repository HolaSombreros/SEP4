# ---------------------------------------------------------------------------------------------------------------------
# ENVIRONMENT VARIABLES
# Define these secrets as environment variables
# ---------------------------------------------------------------------------------------------------------------------

# AWS_ACCESS_KEY_ID
# AWS_SECRET_ACCESS_KEY

# ---------------------------------------------------------------------------------------------------------------------
# REQUIRED PARAMETERS
# You must provide a value for each of these parameters.
# Given these are credentials, security of the values should be considered.
# ---------------------------------------------------------------------------------------------------------------------
variable "name" {
  description = "Name of the database"
  type        = string
}

variable "region" {
  description = "The AWS region to deploy to"
  type        = string
  default = "eu-central-1"
}

variable "vpc_id" {
  default = "vpc-09d9e770ef068133a"
}

variable "subnet_group_id" {
  default = "sep4_public_subnet_group"
}

variable "username" {
  description = "Master username of the DB"
  type        = string
}

variable "password" {
  description = "Master password of the DB"
  type        = string
}


# ---------------------------------------------------------------------------------------------------------------------
# OPTIONAL PARAMETERS
# These parameters have reasonable defaults.
# ---------------------------------------------------------------------------------------------------------------------

variable "engine_name" {
  description = "Name of the database engine"
  type        = string
  default     = "sqlserver-ex"
}

variable "port" {
  description = "Port which the database should run on"
  type        = number
  default     = 3306
}

variable "engine_version" {
  description = "Version of the database to be launched"
  default     = "14.00.3401.7.v1"
  type        = string
}

variable "allocated_storage" {
  description = "Disk space to be allocated to the DB instance"
  type        = number
  default     = 20
}

variable "license_model" {
  description = "License model of the DB instance"
  type        = string
  default     = "license-included"
}

variable "instance_class" {
  description = "Instance class to be used to run the database"
  type        = string
  default     = "db.t2.micro"
}
