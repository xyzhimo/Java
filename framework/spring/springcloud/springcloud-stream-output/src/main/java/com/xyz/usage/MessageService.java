package com.xyz.usage;

import com.xyz.message.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MessageService {

    @Resource
    private Message message;

    public void send(String text) {
        MessageChannel channel = message.output();

        channel.send(MessageBuilder.withPayload(text).build());
    }

}
