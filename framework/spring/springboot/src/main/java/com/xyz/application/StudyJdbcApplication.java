package com.xyz.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sxc.diamond.config", "com.xyz.beans", "com.xyz.configure"})
public class StudyJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyJdbcApplication.class, args);
	}

}
