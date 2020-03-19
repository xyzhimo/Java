package com.xyz.dependency.injection;

import com.xyz.bean.User;
import com.xyz.bean.UserLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowireAnnotationSourceCodeReaderDemo {

    @Autowired
    private User user;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(AutowireAnnotationSourceCodeReaderDemo.class);
        applicationContext.register(UserLoader.class);

        applicationContext.refresh();
        applicationContext.close();
    }
}
