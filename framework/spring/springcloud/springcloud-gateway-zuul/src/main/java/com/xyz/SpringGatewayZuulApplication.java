package com.xyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SpringGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGatewayZuulApplication.class, args);
    }

}
