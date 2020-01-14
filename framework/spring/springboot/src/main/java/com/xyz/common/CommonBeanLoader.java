package com.xyz.common;

import org.springframework.context.annotation.Bean;

public class CommonBeanLoader {

    @Bean
    private CommonBean commonBean() {
        System.err.println("common Bean @Bean loader");
        return new CommonBean();
    }

}
