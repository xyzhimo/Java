package com.xyz.bean.override;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommonBeanConfigure {

    @Bean
    public CommonBean commonBean() {
        System.out.println(11111111);
        return new CommonBean();
    }

    @Primary
    @Bean("commonBean")
    public CommonBeanProxy commonBeanProxy(CommonBean commonBean) {
        System.out.println(2222222);
        return new CommonBeanProxy(commonBean);
    }
}
