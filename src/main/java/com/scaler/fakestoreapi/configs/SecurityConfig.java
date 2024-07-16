package com.scaler.fakestoreapi.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(authorise->authorise.
//                requestMatchers("/product/{id}").hasAuthority("SCOPE_ADMIN").
//                anyRequest().permitAll())
//                .oauth2ResourceServer((oauth)->oauth.jwt(Customizer.withDefaults()));
//            return http.build();
        http
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .anyRequest().permitAll()
                                        .and().cors().disable()
                                        .csrf().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        return http.build();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        // Use the issuer URI from your user-service
//        return JwtDecoders.fromIssuerLocation("http://localhost:8081");
//    }
}
