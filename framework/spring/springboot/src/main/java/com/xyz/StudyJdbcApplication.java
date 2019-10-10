package com.xyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sxc.diamond.config", "com.xyz.beans"})
public class StudyJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyJdbcApplication.class, args);
	}

}
