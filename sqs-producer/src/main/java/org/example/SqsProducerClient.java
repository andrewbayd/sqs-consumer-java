package org.example;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.net.URI;

public class SqsProducerClient {

    private final SqsAsyncClient client;
    private final String queueName;

    public SqsProducerClient(SqsQueueConfig config) {
        client = SqsAsyncClient.builder()
                .endpointOverride(URI.create(config.getEndpoint()))
                .region(Region.of(config.getRegion()))
                .build();
        queueName = config.getQueueName();
    }

    public void sendMessage(String message) {
        SendMessageRequest sendMessageRequest = SendMessageRequest.builder()
                .queueUrl(queueName)
                .messageBody(message)
                .build();
        client.sendMessage(sendMessageRequest).join();
    }
}
