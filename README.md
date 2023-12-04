# Java AWS SQS Consumer Examples

This repository contains POCs of the different implementations of AW SQS consumer in Java.

### AWS SDK SQS Consumer
This POC uses plain AWS SDK to implement consumer and processing logic.
It allows for more fine-grained configuration and implementation of a custom handling logic.
On the contrary, it requires implementation of the SQS client wrapper and asynchronous handling.
All messages in the batch (max 10 messages) are being processed asynchronously in parallel.

### Spring Cloud SQS Consumer
In this example the `@SqsListener` annotation from Spring Cloud library is used to implement consumer.
Spring Cloud encapsulates AWS SDK API providing the way to implement only processing logic.
`@SqsListener` annotation provides parallel asynchronous processing of messages that can be configured.

### JMS SQS Consumer
This example utilizes Java Messaging Library to listen to an SQS queue. 
JMS encapsulates AWS SQS client logic, but brings into the picture additional APIs which require learning curve.
Messages are consumed by one and processed asynchronously. Concurrent processing can be configured by setting the `workersCount` config value greater than 1.

### SQS Producer
Simple producer class to test the consumer implementations

## How to test
To be able to run consumers locally, you need to install
- [Docker](https://docs.docker.com/get-docker/)
- [Localstack](https://docs.localstack.cloud/getting-started/installation/)

When localstack is installed, you can start it with the following command
```shell
localstack start
```

To create an SQS queue for testing, run the following command
```shell
aws --endpoint-url=http://localhost:4566 --region eu-central-1 sqs create-queue --queue-name test-queue
```

When queue is created, you can use `sqs-producer` to publish messages to the queue and any of the consumers to consume