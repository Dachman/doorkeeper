package com.dachlab.doorkeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dachlab.doorkeeper.service.IDoorKeeperService;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.dachlab" })
@EntityScan(basePackages = { "com.dachlab" })
@EnableJpaRepositories(basePackages = "com.dachlab")
public class Application {

	@Autowired
	IDoorKeeperService doorKeeperService;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}
