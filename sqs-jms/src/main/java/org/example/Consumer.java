package org.example;

import jakarta.jms.JMSException;

public interface Consumer {
    void consume() throws JMSException;
    void stop() throws JMSException;
}
