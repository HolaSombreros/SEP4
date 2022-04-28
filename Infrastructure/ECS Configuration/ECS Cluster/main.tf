terraform {
  backend "s3" {
    bucket         = "sep4-terraform-state"
    region         = "eu-central-1"
    dynamodb_table = "sep4-terraform-state-table"
    key            = "ecs-cluster"
  }
}

resource "aws_ecs_cluster" "aws-ecs-cluster" {
  name = "sep4-clusterr"
  tags = {
    Name = "sep4-clusterr"
  }
}

resource "aws_cloudwatch_log_group" "log-group" {
  name = "sep4-cluster-logs"
}
