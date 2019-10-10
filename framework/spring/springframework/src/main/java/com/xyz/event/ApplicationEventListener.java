package com.xyz.event;

import com.xyz.util.ApplicationKeeper;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationEventListener implements ApplicationListener<ApplicationEventListener.HelloApplicationEvent> {

    @Override
    public void onApplicationEvent(HelloApplicationEvent event) {
        System.out.println("receive the event from source:" + event.getSource());
    }

    static class HelloApplicationEvent extends ApplicationEvent {

        HelloApplicationEvent(Object source) {
            super(source);
        }
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});

//        1. context.refresh();

        // 增加监听器
        context.addApplicationListener(new ApplicationEventListener());

//        2. context.refresh();

        // 发布事件
        HelloApplicationEvent event = new HelloApplicationEvent("Hello World!");
        context.publishEvent(event);

//        3. context.refresh();

        new ApplicationKeeper(context).keep();
    }

}
