variable "image_url" {
  type = string
  default = "931417283309.dkr.ecr.eu-central-1.amazonaws.com/sep4-data-service-repo"
}

variable "aws_region" {
  type = string
  default = "eu-central-1"
}

variable "image_tag" {
  type = number
  default = 96
}

variable "task_name" {
  type = string
  default = "sep4-data-service"
}

variable "logs_group" {
  type = string
  default = "sep4-cluster-logs"
}

variable "ecs_cluster_arn" {
  type = string
  default = "arn:aws:ecs:eu-central-1:931417283309:cluster/sep4-clusterr"
}

variable "ecs_subnet" {
  type = list
  default = ["subnet-0e75b7400d0a8fac6"]
}

variable "load_balancer_public_subnet" {
  type = list
  default = ["subnet-0f389750771c3ac8b", "subnet-0e75b7400d0a8fac6"]
}

variable "vpc_id" {
  type = string
  default = "vpc-09d9e770ef068133a"
}

