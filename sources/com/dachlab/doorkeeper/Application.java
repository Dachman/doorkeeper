package com.dachlab.doorkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dachlab.doorkeeper.service.IDoorKeeperService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.dachlab" })
@EntityScan(basePackages = { "com.dachlab" })
@EnableJpaRepositories(basePackages = "com.dachlab")
@Configuration
@PropertySources({ 
	@PropertySource("doorkeeper-${spring.profiles.active:default}.properties"), 
	@PropertySource("imageservice-${spring.profiles.active:default}.properties"),
	@PropertySource("application-${spring.profiles.active:default}.properties") 
})
public class Application {

	@Autowired
	IDoorKeeperService doorKeeperService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
