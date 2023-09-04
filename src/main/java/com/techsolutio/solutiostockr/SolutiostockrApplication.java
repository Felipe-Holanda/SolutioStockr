package com.techsolutio.solutiostockr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.techsolutio.solutiostockr"})
public class SolutiostockrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolutiostockrApplication.class, args);
	}

}
