package org.example;

public interface Consumer<T> {
    void consumeMessage(T message);
}
