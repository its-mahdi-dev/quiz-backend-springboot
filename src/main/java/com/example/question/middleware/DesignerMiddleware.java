package com.example.question.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.question.model.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DesignerMiddleware extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String userType = (String) request.getAttribute("userType");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String path = request.getRequestURI();
        HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(request) {
            @Override
            public Object getAttribute(String name) {
                return super.getAttribute(name);
            }
        };
        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        

        if (!"designer".equals(userType)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            Map<String, String> resp = new HashMap<>();
            resp.put("message", UserResponse.UNAUTHORIZED);
            response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
            return;
        }

        filterChain.doFilter(wrappedRequest, response);
    }
}
