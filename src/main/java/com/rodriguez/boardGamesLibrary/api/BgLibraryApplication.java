package com.rodriguez.boardGamesLibrary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class BgLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BgLibraryApplication.class, args);
	}

}
