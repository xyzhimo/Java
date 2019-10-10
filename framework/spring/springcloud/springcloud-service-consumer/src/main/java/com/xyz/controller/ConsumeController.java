package com.xyz.controller;

import com.xyz.api.UserClient;
import com.xyz.domain.User;
import com.xyz.hystrix.FindHystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URISyntaxException;

@RestController
public class ConsumeController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${spring-cloud-service-provider.hostName}")
    public String serviceProviderHostName;

    @Value("${spring-cloud-service-provider.port}")
    public String serviceProviderPort;

    @Value("${spring-cloud-service-provider.name}")
    public String serviceProviderName;

    @Resource
    private UserClient userClient;


    @GetMapping(value = "/consumeGreeting")
    public String consumeGreeting() throws URISyntaxException {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        RequestEntity requestEntity = new RequestEntity("{\"id\":1,\"name\":\"axiao\"}",
//                headers, HttpMethod.GET, new URI("http://localhost:7005/greeting"));
//        String response = restTemplate.postForObject("http://localhost:7001/greeting", requestEntity,
//                String.class);

        // 415 not support media type
//        String response = restTemplate.postForObject("http://localhost:7001/greeting", "{\"id\":1,
//        \"name\":\"axiao\"}",
//                String.class);

        User user = new User();
        user.setId(1);
        user.setName("axiao");

//        String response = restTemplate.postForObject("http://" + serviceProviderHostName + ":" + serviceProviderPort +
//                "/greeting", user, String.class);

        String response = restTemplate.postForObject("http://" + serviceProviderName + "/greeting", user, String.class);

        return response;
    }

    @GetMapping(value = "/consumeFind")
    public String consumeFind() {
        return new FindHystrixCommand(serviceProviderName, restTemplate).execute();
    }

    @GetMapping(value = "/feignUser")
    public String feignUser() {
        User user = new User();
        user.setId(1);
        user.setName("axiao");
        return userClient.greetUser(user);
    }

}
