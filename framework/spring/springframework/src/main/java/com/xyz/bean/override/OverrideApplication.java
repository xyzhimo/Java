package com.xyz.bean.override;

import com.xyz.annotation.ConfigurableBean;
import com.xyz.annotation.ImportContext;
import com.xyz.bean.SimpleBean0;
import com.xyz.dependency.injection.CustomizedAutowireBeanPostProcessorDemo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OverrideApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CommonBeanConfigure.class);
        applicationContext.refresh();
        CommonBeanProxy bean1 = applicationContext.getBean(CommonBeanProxy.class);
    }

}
