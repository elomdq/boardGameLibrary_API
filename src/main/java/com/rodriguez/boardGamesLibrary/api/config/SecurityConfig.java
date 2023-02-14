package com.rodriguez.boardGamesLibrary.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private AppBasicAuthenticationEntryPoint authEntryPoint;

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        auth
//            .inMemoryAuthentication()
//            .withUser("user")
//            .password(passwordEncoder().encode("password"))
//            .authorities("PUBLIC_USER");
//    }

    @Bean
    public InMemoryUserDetailsManager users() {
        UserDetails admin = User
                .withUsername("admin")
                .password(passwordEncoder().encode("password2"))
                .roles("ADMIN")
                .build();

        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/api/library/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/library/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/library/**/{param}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/library/**/{param}").hasRole("ADMIN")
//                .antMatchers( "/api/library/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);

        http.csrf().disable();
        //http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
