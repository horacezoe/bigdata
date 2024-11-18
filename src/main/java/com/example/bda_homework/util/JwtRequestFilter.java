package com.example.bda_homework.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null) {
            jwt = authorizationHeader;
            try {
                username = jwtUtil.extractClaims(jwt).getSubject();
                logger.debug("JWT Token extracted, username: {}", username);
            } catch (Exception e) {
                logger.error("Error extracting claims from JWT token", e);
            }
        } else {
            //打印authorizationHeader
            System.out.println(authorizationHeader);
            logger.warn("Authorization header is missing or does not start with Bearer");
        }

        // 验证并设置用户信息
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug("User authenticated, username: {}", username);
        } else if (username == null) {
            logger.warn("Username is null, cannot authenticate user");
        } else {
            logger.debug("User already authenticated, username: {}", username);
        }

        chain.doFilter(request, response);
    }
}

