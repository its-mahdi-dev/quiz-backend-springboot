package com.example.question.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.example.question.middleware.AuthMiddleware;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthMiddleware> authFilter() {
        FilterRegistrationBean<AuthMiddleware> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthMiddleware());
        registrationBean.addUrlPatterns("/api/player/*", "/api/designer/*" , "/api/follow/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
