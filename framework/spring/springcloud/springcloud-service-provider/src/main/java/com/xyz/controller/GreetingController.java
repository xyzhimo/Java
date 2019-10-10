package com.xyz.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyz.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private final static Random random = new Random();

    @PostMapping(value = "/user")
    public String greetingUser(@RequestBody User user) {
        return "greeting!" + user + "\n";
    }

    @GetMapping(value = "/find")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100"),
            },
            fallbackMethod = "fallback"
    )
    public String greetingFind() throws InterruptedException {
        int time  = random.nextInt(200);
        System.out.println(time);
        Thread.sleep(time);
        return "found";
    }

    private String fallback() {
        return "provider not found";
    }
}
