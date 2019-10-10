package com.xyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringEurekaClientApplication {


    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(SpringEurekaClientApplication.class);
        springApplication.setWebEnvironment(true);
        springApplication.run(args);

//        SpringApplicationBuilder springApplicationBuilder =
//                new SpringApplicationBuilder(SpringEurekaClientApplication.class);
//        springApplicationBuilder.web(true).run(args);
    }

}
