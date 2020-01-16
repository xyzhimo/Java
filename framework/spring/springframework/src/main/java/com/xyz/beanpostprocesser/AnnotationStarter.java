package com.xyz.beanpostprocesser;

import com.xyz.bean.SimpleBean0;
import com.xyz.bean.SimpleBean1;
import com.xyz.util.ApplicationKeeper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationStarter {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SimpleBean0.class);
        applicationContext.register(HelloBeanPostProcessor.class);
        applicationContext.register(SimpleBean1.class);
        applicationContext.refresh();

        new ApplicationKeeper(applicationContext).keep();
    }
}
