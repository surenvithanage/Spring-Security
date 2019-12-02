package com.security.complete.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 3:19 PM
 */
@Configuration
public class BeanConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Gson getGson() {
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
