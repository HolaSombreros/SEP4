provider "aws" {
  region = var.region
}

terraform {
  backend "s3" {
    bucket = "sep4-terraform-state"
    region = "eu-central-1"
    dynamodb_table = "sep4-terraform-state-table"
    key = "data-service-ecr"
  }
}

resource "aws_ecr_repository" "sep4-data-service" {
  name                 = "sep4-data-service-repo"
  image_tag_mutability = "MUTABLE"
}

//TODO tag prefix list?
resource "aws_ecr_lifecycle_policy" "sep4-data-service-lifecycle-policy" {
  repository = aws_ecr_repository.sep4-data-service.name

  policy = <<EOF
  {
    "rules": [
        {
            "rulePriority": 1,
            "description": "Keep last 5 images",
            "selection": {
                "tagStatus": "tagged",
                "tagPrefixList": ["v"],
                "countType": "imageCountMoreThan",
                "countNumber": 5
            },
            "action": {
                "type": "expire"
            }
        }
    ]
}
EOF
}