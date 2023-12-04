package org.example;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        SqsQueueConfig config = new SqsQueueConfig("http://localhost:4566", "eu-central-1", "test-queue");
        SqsProducerClient sqsProducerClient = new SqsProducerClient(config);

        int n = 200;

        IntStream.range(0, n).parallel().forEach(i -> sqsProducerClient.sendMessage("Message #" + i));
        System.out.println("Publishing finished. Messages published: " + n);
    }
}