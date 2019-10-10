package com.xyz.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Message {

    String INPUT = "message-input";

    @Input(Message.INPUT)
    SubscribableChannel input();

}
