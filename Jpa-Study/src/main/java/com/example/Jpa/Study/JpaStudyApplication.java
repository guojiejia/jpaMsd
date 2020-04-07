package com.example.Jpa.Study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@ComponentScan(basePackages = {"dao","entity","service","controller"})
public class JpaStudyApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JpaStudyApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(JpaStudyApplication.class, args);
	}
}
