package com.xyz.usage;

import com.xyz.message.Message;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;


@Component
public class MessageService {

    @StreamListener(Message.INPUT)
    public void receive(String text) {
        System.err.println(text);
    }

}
