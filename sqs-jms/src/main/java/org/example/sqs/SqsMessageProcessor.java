package org.example.sqs;


import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqsMessageProcessor implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(SqsConsumer.class);

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage txtMessage = (TextMessage) message;
            logger.info("Received the message {}", txtMessage.getText());
            message.acknowledge();
        } catch (JMSException e) {
            logger.error("Error processing message", e);
        }
    }
}
