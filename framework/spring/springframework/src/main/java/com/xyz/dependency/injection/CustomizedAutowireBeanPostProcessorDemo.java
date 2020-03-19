package com.xyz.dependency.injection;

import com.xyz.annotation.XInject;
import com.xyz.bean.User;
import com.xyz.bean.UserLoader;
import com.xyz.bean.XInjectBeanPostProcessorLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CustomizedAutowireBeanPostProcessorDemo {

    @Autowired
    private User user;

    @XInject
    private User xInjectuser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(CustomizedAutowireBeanPostProcessorDemo.class);
        applicationContext.register(UserLoader.class);
        applicationContext.register(XInjectBeanPostProcessorLoader.class);

        applicationContext.refresh();

        CustomizedAutowireBeanPostProcessorDemo demo = applicationContext.getBean(CustomizedAutowireBeanPostProcessorDemo.class);

        System.out.println("xInjectuser:" + demo.xInjectuser);
        System.out.println("user:" + demo.user);
        applicationContext.close();
    }


}
