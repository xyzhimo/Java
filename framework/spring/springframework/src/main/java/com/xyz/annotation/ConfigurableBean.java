package com.xyz.annotation;

import com.xyz.bean.SimpleBean0;
import org.springframework.context.annotation.Bean;

//@Component
public class ConfigurableBean {

    @Bean
    public SimpleBean0 simpleBean0() {
        SimpleBean0 simpleBean0 = new SimpleBean0();
        simpleBean0.setId(1);
        simpleBean0.setName("zhimo");
        return simpleBean0;
    }

}
