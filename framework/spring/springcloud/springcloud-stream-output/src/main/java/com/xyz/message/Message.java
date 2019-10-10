package com.xyz.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface Message {

    String OUTPUT = "message-output";

    @Output(Message.OUTPUT)
    SubscribableChannel output();

}
