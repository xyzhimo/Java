package com.xyz.annotation;

import com.xyz.bean.SimpleBean0;
import com.xyz.dependency.injection.CustomizedAutowireBeanPostProcessorDemo;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;

public class AnnotationContextDemo {

    public static void main(String[] args) {
//        importMethod1();
//        beanMethod2();
//        componentMethod3();
        autowiredMethod4();
    }

    public static void importMethod1() {
        // ================== 1 ================= @Import
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ImportContext.class);
        applicationContext.refresh();
        SimpleBean0 bean = applicationContext.getBean(SimpleBean0.class);
        System.out.println("找到 SimpleBean0 bean: " + bean);
        applicationContext.close();
    }

    public static void beanMethod2() {
        // ================== 2 ================= @Bean
        // 先把 ConfigurableBean 的 @Component 注解去掉
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ConfigurableBean.class);
        applicationContext.refresh();
        SimpleBean0 bean = applicationContext.getBean(SimpleBean0.class);
        ConfigurableBean configurableBean = applicationContext.getBean(ConfigurableBean.class);
        System.out.println("找到 SimpleBean0 bean: " + bean);
        System.out.println("找到 ConfigurableBean bean: " + configurableBean);
        applicationContext.close();
    }

    public static void componentMethod3() {
        // ================== 3 ================= @Component
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ConfigurableBean.class);
        applicationContext.refresh();
        SimpleBean0 bean = applicationContext.getBean(SimpleBean0.class);
        ConfigurableBean configurableBean = applicationContext.getBean(ConfigurableBean.class);
        System.out.println("找到 SimpleBean0 bean: " + bean);
        System.out.println("找到 ConfigurableBean bean: " + configurableBean);
        applicationContext.close();
    }


    public static void autowiredMethod4() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(CustomizedAutowireBeanPostProcessorDemo.class);
        applicationContext.refresh();
        CustomizedAutowireBeanPostProcessorDemo demo = applicationContext.getBean(CustomizedAutowireBeanPostProcessorDemo.class);
        System.out.println(demo.user);
    }

}
