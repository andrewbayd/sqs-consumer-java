package org.example.sqs;

import org.example.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SqsMessageHandler implements MessageHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(SqsMessageHandler.class);

    @Override
    public void handleMessage(String message) {
        logger.info("Processing message {}", message);
    }

    @Override
    public void handleError(Throwable e) {
        logger.error("Error during message processing", e);
    }
}
