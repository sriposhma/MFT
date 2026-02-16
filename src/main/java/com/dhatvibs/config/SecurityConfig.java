/*
 * package com.dhatvibs.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.http.SessionCreationPolicy; import
 * org.springframework.security.web.SecurityFilterChain;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * 
 * 
 * @Configuration
 * 
 * @EnableWebSecurity
 * 
 * @RequiredArgsConstructor public class SecurityConfig {
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * 
 * http .csrf(csrf -> csrf.disable()) // 🔥 VERY IMPORTANT
 * .authorizeHttpRequests(auth -> auth .requestMatchers(
 * "/api/teamlead/register", "/api/teamlead/login", "/api/management/register",
 * "/api/management/login", "/v3/api-docs/**", "/swagger-ui/**",
 * "/swagger-ui.html" ).permitAll() .anyRequest().permitAll() // 🔥 allow all
 * for now ) .sessionManagement(session ->
 * session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) );
 * 
 * return http.build(); } }
 */

package com.dhatvibs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})   // 🔥 VERY IMPORTANT (THIS FIXES YOUR ISSUE)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/api/teamlead/register",
                            "/api/teamlead/login",
                            "/api/management/register",
                            "/api/management/login",
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                    ).permitAll()
                    .anyRequest().permitAll()
            )
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

        return http.build();
    }
}
