package org.example;


import org.example.sqs.SqsConsumer;
import org.example.sqs.SqsMessageHandler;
import org.example.sqs.SqsMessageQueueListener;
import org.example.sqs.SqsQueueConfig;
import software.amazon.awssdk.services.sqs.model.Message;

public class Main {
    public static void main(String[] args) {
        SqsQueueConfig config = new SqsQueueConfig("http://localhost:4566", "eu-central-1", "test-queue");
        MessageHandler<String> messageHandler = new SqsMessageHandler();
        Consumer<Message> consumer = new SqsConsumer(config, messageHandler);
        MessageQueueListener listener = new SqsMessageQueueListener(consumer, 1);

        listener.runConsumer();
    }
}