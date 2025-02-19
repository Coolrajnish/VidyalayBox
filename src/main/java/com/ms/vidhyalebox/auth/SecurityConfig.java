package com.ms.vidhyalebox.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.ms.vidhyalebox.user.UserServiceImpl;

import io.swagger.v3.oas.models.PathItem.HttpMethod;

@Configuration
public class SecurityConfig {

    private final UserServiceImpl userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(@Lazy UserServiceImpl userService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

 /*   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for stateless API
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/org/**").permitAll()
//                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/role/**").permitAll()
                .requestMatchers("/health/**").permitAll() // Allow unauthenticated access to /health
                .requestMatchers("/error").permitAll()   // Allow unauthenticated access to /error
                .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll() // Allow Swagger access
//                .requestMatchers("/root-admin/**").hasRole("ROOT_ADMIN")
//                .requestMatchers("/school/**").hasRole("SCHOOL_ADMIN")
//                .requestMatchers("/student/**").hasRole("STUDENT")
//                .requestMatchers("/teacher/**").hasRole("TEACHER")
                .requestMatchers("/url/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session management
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Authorization filter before username/password filter

        return http.build();
    }*/


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF for stateless API
            .authorizeRequests()
                .requestMatchers("/auth/**", "/role/**", "/health/**", "/error", "/v3/api-docs/**", 
                                  "/swagger-ui/**", "/swagger-ui.html", "/webjars/**", "/url/**")
                    .permitAll()  // Allow public access to these endpoints
                .anyRequest().authenticated()  // All other endpoints require authentication
            .and()
            .cors()  // Enable CORS for all requests
            .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())  // Default CORS configuration
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session management
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Authorization filter before username/password filter

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}