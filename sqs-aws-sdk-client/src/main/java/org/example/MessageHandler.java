package org.example;

public interface MessageHandler<T> {
    void handleMessage(T message);
    void handleError(Throwable e);
}
