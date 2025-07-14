package com.productmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("admin")
                               .password("{noop}password") // {noop} disables encoding
                               .roles("USER")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		System.out.println(http);
		 http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
             // ✅ Allow swagger-ui & OpenAPI docs without authentication
             .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
             // ✅ Require auth on your protected REST endpoints
             .requestMatchers("/api/**").authenticated()
             // ✅ Allow everything else (static resources, health, etc)
             .anyRequest().permitAll()
         )
         // ✅ Use basic HTTP auth
         .httpBasic();
	        return http.build();
	    }
}