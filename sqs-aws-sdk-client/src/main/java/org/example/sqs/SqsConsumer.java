package org.example.sqs;

import org.example.Consumer;
import org.example.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

public class SqsConsumer implements Consumer<Message> {
    private final SqsConsumerClient client;
    private final MessageHandler<String> messageHandler;

    private final ForkJoinPool executor = new ForkJoinPool();
    private final Logger logger = LoggerFactory.getLogger(SqsConsumer.class);

    public SqsConsumer(SqsQueueConfig config, MessageHandler<String> messageHandler) {
        this.client = new SqsConsumerClient(config);
        this.messageHandler = messageHandler;
    }

    @Override
    public void consume() {
        try {
            var tasks = receiveMessages().stream().map(
                    message -> CompletableFuture.runAsync(() -> processMessage(message), executor)
            ).toList();

            tasks.forEach(CompletableFuture::join);
        } catch (Exception e) {
            logger.error("Error occurred while processing messages", e);
        }
    }

    private List<Message> receiveMessages() {

        try {
            return client.receiveMessages();
        } catch (SqsException e) {
            logger.error("Error occurred while receiving messages", e);
        }
        return Collections.emptyList();
    }

    private void processMessage(Message message) {
        try {
            messageHandler.handleMessage(message.body());
        } catch (Exception e) {
            messageHandler.handleError(e);
        } finally {
            client.deleteMessage(message);
        }
    }
}
