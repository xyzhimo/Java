package com.xyz;

import com.xyz.message.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Message.class)
public class SpringStreamInputApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStreamInputApplication.class, args);
    }

}
