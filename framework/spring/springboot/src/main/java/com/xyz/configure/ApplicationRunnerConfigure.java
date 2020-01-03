package com.xyz.configure;

import com.xyz.common.CommonBean;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

//    @EventListener(WebServerInitializedEvent.class)
//    public void onWebServerReady(WebServerInitializedEvent event) {
//        System.err.println(event.getWebServer().getClass().getName());
//    }
}
