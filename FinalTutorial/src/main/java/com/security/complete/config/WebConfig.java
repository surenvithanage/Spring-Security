package com.security.complete.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 3:27 PM
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * Method: addCorsMappings
     * Params: registry
     * Description: Enable cross origin
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "cache-control")
                .exposedHeaders("Authorization", "UserID").allowCredentials(true).maxAge(3600);
    }
}
