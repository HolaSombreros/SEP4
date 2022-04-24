provider "aws" {
  region = "eu-central-1"
}

terraform {
  backend "s3" {
    bucket = "sep4-terraform-state"
    region = "eu-central-1"
    dynamodb_table = "sep4-terraform-state-table"
    key = "s3-bucket-dockerrun"
  }
}

# Create a bucket
resource "aws_s3_bucket" "dockerrun_bucket" {
  bucket = "sep4-dockerrun-bucket"

  tags = {
    Name        = "DockerRun bucket"
  }
}