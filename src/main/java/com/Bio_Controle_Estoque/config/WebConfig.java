package com.Bio_Controle_Estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Permite apenas para localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Define os métodos permitidos
                .allowedHeaders("*"); // Permite todos os headers
    }
}