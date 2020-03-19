package com.xyz.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class UserLoader {

    @Bean
    public User user() {
        User user = new User();
        user.setId(1);
        user.setName("xiao1");
        return user;
    }

    @Bean
    @Primary
    public User superUser() {
        SuperUser user = new SuperUser();
        user.setId(1);
        user.setName("xiao1");
        user.setAddress("HANGZHOU");
        return user;
    }

}
