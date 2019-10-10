package com.xyz.event;

import com.xyz.util.ApplicationKeeper;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

public class AnnotationApplicationEventListener {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(EventConfiguration.class);

        applicationContext.refresh();

        ApplicationEventPublisher eventPublisher = applicationContext;

        eventPublisher.publishEvent(new MyEvent("hello"));

        new ApplicationKeeper(applicationContext).keep();

    }

    private static class MyEvent extends ApplicationEvent {

        private static final long serialVersionUID = 5814369165461816751L;

        /**
         * Create a new ApplicationEvent.
         *
         * @param source the object on which the event initially occurred (never {@code null})
         */
        public MyEvent(String source) {
            super(source);
        }
    }

    @Configuration
    public static class EventConfiguration {

        @EventListener()
        public void onEvent(ApplicationEvent event) {
            System.out.println(event.getSource());
        }

    }

}
