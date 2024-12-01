package com.viktoria.spring.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static com.viktoria.spring.database.entity.Role.ADMIN;


@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((urlConfig) -> urlConfig
                        .requestMatchers("/login", "/users/registration", "/v3/api-docs/**", "/swagger-ui/**",
                                "/sups","/api/v1/messages").permitAll()
                        .requestMatchers("/users/{userId:\\d+}/delete", "/users").hasAuthority(ADMIN.getAuthority())
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .usernameParameter("login")
                        .defaultSuccessUrl("/sups")
                )
                .build();
    }

}
