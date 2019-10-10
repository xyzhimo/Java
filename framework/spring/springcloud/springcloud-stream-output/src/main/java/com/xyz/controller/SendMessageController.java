package com.xyz.controller;

import com.xyz.usage.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private MessageService messageService;

    @GetMapping(value = "/send/rabbit/message/{text}")
    public boolean sendRabbitMessage(@PathVariable(value = "text") String text) {
        messageService.send(text);
        return true;
    }

}
