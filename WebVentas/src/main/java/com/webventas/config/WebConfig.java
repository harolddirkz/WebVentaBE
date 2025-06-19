package com.webventas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica CORS a todos los endpoints
                .allowedOrigins("http://localhost:3000") // Origen permitido
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") // Métodos permitidos
                .allowedHeaders("*") // Encabezados permitidos
                .allowCredentials(true); // Permite credenciales (cookies, autenticación)
    }
}