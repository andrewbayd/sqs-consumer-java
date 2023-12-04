package org.example.sqs;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.example.Consumer;
import org.example.MessageHandler;
import org.springframework.stereotype.Service;

@Service
public class SqsConsumer implements Consumer<String> {
    private final MessageHandler<String> messageHandler;

    public SqsConsumer(MessageHandler<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    @SqsListener("test-queue")
    public void consumeMessage(String message) {
        try {
            messageHandler.handleMessage(message);
        } catch (Exception e) {
            messageHandler.handleError(e);
        }
    }
}
