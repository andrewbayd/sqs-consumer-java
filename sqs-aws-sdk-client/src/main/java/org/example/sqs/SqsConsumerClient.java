package org.example.sqs;

import org.example.ConsumerClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.net.URI;
import java.util.List;

public class SqsConsumerClient implements ConsumerClient<Message> {
    private final SqsClient client;
    private final String queueName;

    public SqsConsumerClient(SqsQueueConfig config) {
        client = SqsClient.builder()
                .endpointOverride(URI.create(config.getEndpoint()))
                .region(Region.of(config.getRegion()))
                .build();
        queueName = config.getQueueName();
    }

    public List<Message> receiveMessages() {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueName)
                .maxNumberOfMessages(10)
                .waitTimeSeconds(20)
                .build();

        return client.receiveMessage(request).messages();
    }

    public boolean deleteMessage(Message message) {
        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueName)
                .receiptHandle(message.receiptHandle())
                .build();

        return client.deleteMessage(deleteRequest).sdkHttpResponse().isSuccessful();
    }
}
