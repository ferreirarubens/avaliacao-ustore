package com.ferreirarubens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ferreirarubens.services.IStorageService;

@SpringBootApplication
@EnableJpaRepositories
public class AvaliacaoUstoreApplication extends SpringBootServletInitializer {

	@Autowired
	private IStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoUstoreApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AvaliacaoUstoreApplication.class);
	}
	
	@Bean
    CommandLineRunner init() {
        return (args) -> {
            storageService.deleteAll();
            storageService.start();
        };
    }
}
