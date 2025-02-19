package com.ms.vidhyalebox.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")  // Allow CORS for all endpoints
	                .allowedOrigins("http://localhost:5173")  // Allow frontend from localhost:5173
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")  // Allow specific HTTP methods
	                .allowedHeaders("Authorization", "Content-Type", "Accept", "X-Requested-With")  // Allow necessary headers
	                .allowCredentials(true);  // Allow credentials (cookies, authorization headers)
	    }
}
