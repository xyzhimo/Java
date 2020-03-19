package com.xyz.dependency.injection;

import com.xyz.bean.PriorityOrderedBeanPostProcessor;
import com.xyz.bean.UserLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PriorityOrderedBeanPostProcessorDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(PriorityOrderedBeanPostProcessor.class);
        applicationContext.register(UserLoader.class);

        applicationContext.refresh();

        PriorityOrderedBeanPostProcessor processor = applicationContext.getBean(PriorityOrderedBeanPostProcessor.class);
        System.out.println(processor.user);

        applicationContext.close();
    }


}
