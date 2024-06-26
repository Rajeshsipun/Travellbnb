package com.travelbnb.travelbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter ;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http ) throws Exception {

        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/v1/user/login","/api/v1/user/createUser")
//                .permitAll()
//                .requestMatchers("/api/v1/countries/addCountry").hasRole("ADMIN")
//                .requestMatchers("/api/v1/photos/upload").hasAnyRole("ADMIN","USER")
//                .anyRequest().authenticated();
        ;


        return http.build();
    }
}
