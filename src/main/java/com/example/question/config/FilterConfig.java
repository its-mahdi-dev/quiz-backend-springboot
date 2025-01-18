package com.example.question.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.example.question.middleware.AuthMiddleware;
import com.example.question.middleware.DesignerMiddleware;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthMiddleware> authFilter() {
        FilterRegistrationBean<AuthMiddleware> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthMiddleware());
        registrationBean.addUrlPatterns("/api/player/*", "/api/designer/*"); // Apply to both player and designer
        registrationBean.setOrder(1); // AuthMiddleware should be executed first
        return registrationBean;
    }

    // @Bean
    // public FilterRegistrationBean<DesignerMiddleware> designerFilter() {
    //     FilterRegistrationBean<DesignerMiddleware> registrationBean = new FilterRegistrationBean<>();
    //     registrationBean.setFilter(new DesignerMiddleware());
    //     registrationBean.addUrlPatterns("/api/designer/*"); // Apply only to designer routes
    //     registrationBean.setOrder(2); // DesignerMiddleware should be executed after AuthMiddleware
    //     return registrationBean;
    // }
}
