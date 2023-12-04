package org.example.sqs;

public class SqsQueueConfig {
    private final String endpoint;
    private final String region;
    private final String name;
    private final int workersCount;

    public SqsQueueConfig(String endpoint, String region, String name, int workersCount) {
        this.endpoint = endpoint;
        this.region = region;
        this.name = name;
        this.workersCount = workersCount;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public int getWorkersCount() {
        return workersCount;
    }
}
