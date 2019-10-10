package com.xyz;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder =
                new SpringApplicationBuilder(SpringEurekaServerApplication.class);
        springApplicationBuilder.web(true).run(args);
    }

}
