package com.example.player.middleware;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.player.model.UserResponse;
import com.example.player.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.handler.logging.LogLevel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AuthMiddleware extends OncePerRequestFilter {

    private static final Logger logger = Logger.getLogger(AuthMiddleware.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();


        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            respondWithUnauthorized(response);
            return;
        }

        String token = authorizationHeader.substring(7);

        try {
            DecodedJWT decodedJWT = JwtUtil.verifyToken(token);
            Long userId = decodedJWT.getClaim("id").asLong();
            String userType = decodedJWT.getClaim("type").asString();
            request.setAttribute("userId", userId);
            request.setAttribute("userType", userType);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            respondWithUnauthorized(response);
        }
    }

    private void respondWithUnauthorized(HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", UserResponse.UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
    }
}
