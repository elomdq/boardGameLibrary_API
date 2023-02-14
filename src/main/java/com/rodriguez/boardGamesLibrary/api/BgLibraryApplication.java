package com.rodriguez.boardGamesLibrary.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class BgLibraryApplication {

	public static void main(String[] args) {

		SpringApplication.run(BgLibraryApplication.class, args);

		//Solo probando las interfases de Spring Security
//		SecurityContext context = SecurityContextHolder.createEmptyContext();
//		Authentication authentication = new TestingAuthenticationToken("user","password","ROLE_USER");
//		context.setAuthentication(authentication);
//		SecurityContextHolder.setContext(context);


	}

}
