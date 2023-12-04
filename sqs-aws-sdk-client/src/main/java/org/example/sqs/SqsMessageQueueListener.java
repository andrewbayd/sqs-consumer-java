package org.example.sqs;

import org.example.Consumer;
import org.example.MessageQueueListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sqs.model.Message;

import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SqsMessageQueueListener implements MessageQueueListener {
    private final Consumer<Message> consumer;
    private final ScheduledThreadPoolExecutor workers;

    private final Logger logger = LoggerFactory.getLogger(SqsMessageQueueListener.class);

    public SqsMessageQueueListener(Consumer<Message> consumer, int workersCount) {
        this.consumer = consumer;
        this.workers = new ScheduledThreadPoolExecutor(workersCount);
    }

    @Override
    public void runConsumer() {
        workers.scheduleWithFixedDelay(()-> {
            try {
                logger.info("Polling for messages...");
                consumer.consume();
            } catch (Exception e) {
                logger.error("Error occurred while processing messages", e);
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
    }


    @Override
    @PreDestroy
    public void stopConsumer() {
        workers.shutdown();
    }
}
