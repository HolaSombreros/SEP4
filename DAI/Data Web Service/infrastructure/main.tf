terraform {
  backend "s3" {
    bucket         = "sep4-terraform-state"
    region         = "eu-central-1"
    dynamodb_table = "sep4-terraform-state-table"
    key            = "data-service-task"
  }
}

resource "aws_ecs_task_definition" "aws-ecs-task" {
  family = "${var.task_name}-task"

  container_definitions = <<DEFINITION
  [
    {
      "name": "${var.task_name}-container",
      "image": "${var.image_url}:${var.image_tag}",
      "entryPoint": [],
      "essential": true,
      "logConfiguration": {
        "logDriver": "awslogs",
        "options": {
          "awslogs-group": "${var.logs_group}",
          "awslogs-region": "${var.aws_region}",
          "awslogs-stream-prefix": "${var.task_name}"
        }
      },
      "portMappings": [
        {
          "containerPort": 8888,
          "hostPort": 8888
        }
      ],
      "cpu": 256,
      "memory": 512,
      "networkMode": "awsvpc"
    }
  ]
  DEFINITION

  requires_compatibilities = ["FARGATE"]
  network_mode             = "awsvpc"
  memory                   = "512"
  cpu                      = "256"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  tags = {
    Name        = "${var.task_name}-ecs-td"
  }
}

data "aws_ecs_task_definition" "main" {
  task_definition = aws_ecs_task_definition.aws-ecs-task.family
}

resource "aws_ecs_service" "aws-ecs-service" {
  name                 = "${var.task_name}-ecs-service"
  cluster              = var.ecs_cluster_arn
  task_definition      = "${aws_ecs_task_definition.aws-ecs-task.family}:${max(aws_ecs_task_definition.aws-ecs-task.revision, data.aws_ecs_task_definition.main.revision)}"
  launch_type          = "FARGATE"
  scheduling_strategy  = "REPLICA"
  desired_count        = 1
  force_new_deployment = true

  network_configuration {
    subnets          = var.ecs_subnet
    assign_public_ip = true
    security_groups  = [
      aws_security_group.service_security_group.id,
      aws_security_group.load_balancer_security_group.id
    ]
  }
  load_balancer {
    target_group_arn = aws_lb_target_group.target_group.arn
    container_name   = "${var.task_name}-container"
    container_port   = 8888
  }

  depends_on = [aws_lb_listener.listener]
}

resource "aws_security_group" "service_security_group" {
  vpc_id = var.vpc_id

  ingress {
    from_port       = 0
    to_port         = 0
    protocol        = "-1"
    security_groups = [aws_security_group.load_balancer_security_group.id]
  }

  egress {
    from_port        = 0
    to_port          = 0
    protocol         = "-1"
    cidr_blocks      = ["0.0.0.0/0"]
    ipv6_cidr_blocks = ["::/0"]
  }

  tags = {
    Name        = "${var.task_name}-service-sg"
  }
}
