package com.xyz.beans;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class HelloBeans {

    @PostConstruct
    public void post1() {
        System.out.println(111);
    }

    @PostConstruct
    public void post2() {
        System.out.println(222);
    }
}
