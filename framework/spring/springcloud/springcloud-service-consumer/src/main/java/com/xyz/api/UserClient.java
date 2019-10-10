package com.xyz.api;

import com.xyz.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "${spring-cloud-service-provider.name}")
public interface UserClient {

    @PostMapping(value = "/greeting")
    String greetUser(User user);

}
