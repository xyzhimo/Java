package com.xyz.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class FindHystrixCommand extends HystrixCommand<String> {

    private String serviceProviderName;

    private RestTemplate restTemplate;

    public FindHystrixCommand(String serviceProviderName, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("service-consumer"), 100);
        this.serviceProviderName = serviceProviderName;
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() {
        return restTemplate.getForObject("http://" + serviceProviderName + "/find", String.class);
    }

    @Override
    protected String getFallback() {
        return "client time out";
    }
}
