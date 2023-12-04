package org.example.sqs;

import org.example.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqsMessageHandler implements MessageHandler<String> {
    private final Logger logger = LoggerFactory.getLogger(SqsMessageHandler.class);

    @Override
    public void handleMessage(String message) {
        logger.info("Handling message: {}",  message);
    }

    @Override
    public void handleError(Throwable e) {
        logger.error("Error occurred while processing the message", e);
    }
}
