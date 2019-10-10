package com.xyz.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The type Application keeper.
 */
@Slf4j
public class ApplicationKeeper {

    private final ReentrantLock LOCK = new ReentrantLock();
    private final Condition STOP = LOCK.newCondition();

    /**
     * Instantiates a new Application keeper.
     *
     * @param applicationContext the application context
     */
    public ApplicationKeeper(AbstractApplicationContext applicationContext) {
        addShutdownHook(applicationContext);
    }

    private void addShutdownHook(final AbstractApplicationContext applicationContext) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    applicationContext.close();
                    log.info("ApplicationContext " + applicationContext + " is closed.");
                } catch (Exception e) {
                    log.error("Failed to close ApplicationContext", e);
                }

                try {
                    LOCK.lock();
                    STOP.signal();
                } finally {
                    LOCK.unlock();
                }
            }
        }));
    }

    /**
     * Keep.
     */
    public void keep() {
        try {
            LOCK.lock();
            log.info("Application is keep running ... ");
            STOP.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 2. Account service is ready . A buyer register an account: U100001 on my e-commerce platform
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});

        new ApplicationKeeper(context).keep();
    }
}