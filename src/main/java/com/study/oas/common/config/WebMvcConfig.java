package com.study.oas.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String API_LOGIN = "api/login";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/api/login").setViewName(API_LOGIN);
    }
}
