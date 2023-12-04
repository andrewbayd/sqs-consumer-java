package org.example;

public class SqsQueueConfig {
    private final String endpoint;
    private final String region;
    private final String queueName;

    public SqsQueueConfig(String endpoint, String region, String queueName) {
        this.endpoint = endpoint;
        this.region = region;
        this.queueName = queueName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getRegion() {
        return region;
    }

    public String getQueueName() {
        return queueName;
    }
}
