package org.example;

import jakarta.jms.JMSException;
import org.example.sqs.SqsConsumer;
import org.example.sqs.SqsQueueConfig;

public class Main {
    public static void main(String[] args) throws JMSException {
        SqsQueueConfig config = new SqsQueueConfig(
                "http://localhost:4566",
                "eu-central-1",
                "test-queue",
                10
        );
        Consumer consumer = new SqsConsumer(config);
        consumer.consume();
    }
}