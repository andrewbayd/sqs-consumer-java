package org.example.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import org.example.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import javax.annotation.PreDestroy;
import java.net.URI;

public class SqsConsumer implements Consumer {
    private final SQSConnection connection;
    private final Queue queue;
    private final int workersCount;

    private final Logger logger = LoggerFactory.getLogger(SqsConsumer.class);

    public SqsConsumer(SqsQueueConfig config) throws JMSException {
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                SqsClient.builder()
                        .endpointOverride(URI.create(config.getEndpoint()))
                        .region(Region.of(config.getRegion()))
                        .build()

        );

        connection = connectionFactory.createConnection();
        queue = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE).createQueue(config.getName());
        workersCount = config.getWorkersCount();
    }

    public void consume() throws JMSException {
        logger.info("Starting consumer...");
        for (int i = 0; i < workersCount; i++) {
            createListener();
        }
        connection.start();
    }

    private void createListener() throws JMSException {
        connection
                .createSession(false, Session.CLIENT_ACKNOWLEDGE)
                .createConsumer(queue)
                .setMessageListener(new SqsMessageProcessor());
    }

    @PreDestroy
    public void stop() throws JMSException {
        logger.info("Stopping consumer...");
        connection.stop();
    }
}
