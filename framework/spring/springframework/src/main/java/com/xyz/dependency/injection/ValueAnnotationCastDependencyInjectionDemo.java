package com.xyz.dependency.injection;

import com.xyz.bean.UserLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

public class ValueAnnotationCastDependencyInjectionDemo {

    @Value("1")
    private Long id;

    @Value("classpath:/spring.xml")
    private Resource resource;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(UserLoader.class);
        applicationContext.register(ValueAnnotationCastDependencyInjectionDemo.class);

        applicationContext.refresh();

        ValueAnnotationCastDependencyInjectionDemo demo = applicationContext.getBean(ValueAnnotationCastDependencyInjectionDemo.class);
        System.out.println(demo.id);
        System.out.println(demo.resource);

        applicationContext.close();
    }
}
