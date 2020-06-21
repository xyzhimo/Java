package com.xyz.configure;

import com.xyz.common.CommonBean;
import com.xyz.common.CommonBeanProxy;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
//@Component
public class ApplicationRunnerConfigure {

    @Bean
    public ApplicationRunner runner(WebServerApplicationContext applicationContext) {
        return args -> {
            System.err.println(applicationContext.getWebServer().getClass().getName());
            System.err.println("ApplicationRunnerConfigure Bean className:" + applicationContext.getBean("applicationRunnerConfigure").getClass().getName());
            System.err.println("CommonBean className:" + applicationContext.getBean("commonBean").getClass().getName());
        };
    }


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

//    @EventListener(WebServerInitializedEvent.class)
//    public void onWebServerReady(WebServerInitializedEvent event) {
//        System.err.println(event.getWebServer().getClass().getName());
//    }
}
