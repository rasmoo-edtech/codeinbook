package com.rasmoo.codeinbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CodeinbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeinbookApplication.class, args);
	}

}
