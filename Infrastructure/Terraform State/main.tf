terraform {
  backend "s3" {
    bucket = "sep4-terraform-state"
    region = "eu-central-1"
    dynamodb_table = "sep4-terraform-state-table"
    key = "terraform-state"
  }
}

resource "aws_dynamodb_table" "sep4-terraform-state-table" {
  name = "sep4-terraform-state-table"
  read_capacity= "20"
  write_capacity= "20"
  hash_key = "LockID"
  attribute {
    name = "LockID"
    type = "S"
  }
}