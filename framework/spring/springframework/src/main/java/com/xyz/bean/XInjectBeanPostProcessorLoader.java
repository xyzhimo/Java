package com.xyz.bean;

import com.xyz.annotation.XInject;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;

public class XInjectBeanPostProcessorLoader {

    @Bean(name = "xInjectBeanPostProcessor")
    public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(XInject.class);
        return beanPostProcessor;
    }

}
