package com.ms.vidhyalebox.auth;

import com.ms.vidhyalebox.user.UserServiceImpl;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .csrf().disable()  // Disable CSRF for stateless API
                .authorizeHttpRequests()
                .requestMatchers("/auth/**").permitAll()  // Allow unauthenticated access to /auth
                .requestMatchers("/role/**").permitAll()  // Allow unauthenticated access to /role
                .requestMatchers("/health/**").permitAll()  // Allow unauthenticated access to /health
                .requestMatchers("/error").permitAll()  // Allow unauthenticated access to /error
                .requestMatchers("/v3/api-docs/**").permitAll()  // Allow Swagger API docs access
                .requestMatchers("/swagger-ui/**").permitAll()  // Allow Swagger UI access
                .requestMatchers("/swagger-ui.html").permitAll()  // Allow access to Swagger UI HTML page
                .requestMatchers("/webjars/**").permitAll()  // Allow access to webjars (for Swagger UI resources)
                .requestMatchers("/url/**").permitAll()  // Allow unauthenticated access to /url
                .anyRequest().authenticated()  // All other endpoints require authentication
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
