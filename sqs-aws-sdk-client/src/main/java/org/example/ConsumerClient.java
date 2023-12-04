package org.example;

import java.util.List;

public interface ConsumerClient<T> {
    List<T> receiveMessages();
    boolean deleteMessage(T message);
}
