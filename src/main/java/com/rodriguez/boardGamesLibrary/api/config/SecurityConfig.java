package com.rodriguez.boardGamesLibrary.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{


    @Autowired
    private AppBasicAuthenticationEntryPoint authEntryPoint;

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails admin = User
                .withUsername("")
                .password(passwordEncoder().encode(""))
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("")
                .password(passwordEncoder().encode(""))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests()
                .antMatchers( "/api/library/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/library/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/library/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/library/**/{param}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/library/**/{param}").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);

        http.csrf().disable(); //no es necesario en esta api
        http.cors();

        http.headers().frameOptions().disable(); //para habilitar el uso de la consola de hs en frame

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
