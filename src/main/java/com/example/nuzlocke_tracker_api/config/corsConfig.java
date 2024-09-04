package com.example.nuzlocke_tracker_api.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class corsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // This allows CORS for all endpoints
                .allowedOrigins("*") // Allow all origins. Replace "*" with specific origins if needed.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HTTP methods
                .allowedHeaders("*") // Allow all headers. Replace "*" with specific headers if needed.
                .allowCredentials(true) // If you need to allow credentials (cookies, authorization headers, etc.)
                .maxAge(3600); // Cache the response for 3600 seconds (1 hour)
    }
}
