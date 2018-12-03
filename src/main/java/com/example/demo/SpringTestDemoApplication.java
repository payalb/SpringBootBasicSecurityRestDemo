package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.demo.config.SpringSecurityConfig;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.example.demo.dao")
@EnableTransactionManagement(proxyTargetClass=false)
//@EnableResourceServer
@Import(SpringSecurityConfig.class)
public class SpringTestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestDemoApplication.class, args);
	}
}
