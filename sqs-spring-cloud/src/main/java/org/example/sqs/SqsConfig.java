package org.example.sqs;

import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.ListenerMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.net.URI;
import java.time.Duration;

@Configuration
public class SqsConfig {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.endpoint}")
    private String endpoint;

    @Value("${aws.sqs.consumer.maxMessagePerPoll}")
    private int maxMessagesPerPoll;

    @Value("${aws.sqs.consumer.pollTimeout}")
    private int pollTimeout;

    @Value("${aws.sqs.consumer.maxConcurrentMessages}")
    private int maxConcurrentMessages;

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(region))
                .endpointOverride(URI.create(endpoint))
                .build();
    }

    @Bean
    public SqsMessageListenerContainerFactory defaultSqsListenerContainerFactory(SqsAsyncClient sqsAsyncClient) {
        return SqsMessageListenerContainerFactory
                .builder()
                .configure(options -> options
                        .listenerMode(ListenerMode.BATCH)
                        .maxMessagesPerPoll(maxMessagesPerPoll)
                        .pollTimeout(Duration.ofSeconds(pollTimeout))
                        .maxConcurrentMessages(maxConcurrentMessages)
                )
                .sqsAsyncClient(sqsAsyncClient)
                .build();
    }
}
