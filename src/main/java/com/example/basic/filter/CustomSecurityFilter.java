package com.example.basic.filter;


import com.example.basic.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class CustomSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Bỏ qua các yêu cầu đến /api/v1/login (vì login không yêu cầu token)
        if (request.getRequestURI().equals("/api/v1/login")) {
            filterChain.doFilter(request, response);
            return;  // Bỏ qua filter này đối với login
        }
        if (request.getRequestURI().equals("/api/v1/login/blacklisted")) {
            filterChain.doFilter(request, response);
            return;  // Bỏ qua filter này đối với blacklisted
        }
        if (request.getRequestURI().equals("/api/v1/kafka")) {
            filterChain.doFilter(request, response);
            return;  // Bỏ qua filter này đối với kafka
        }
        if (request.getRequestURI().equals("/swagger-ui/index.html")) {
            filterChain.doFilter(request, response);
            return;  // Bỏ qua filter này đối với swagger
        }

//        String authHeader = request.getHeader("Authorization");
//        if(authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            boolean isSuccess = jwtHelper.verifyToken(token);
//            if(isSuccess) {
//                logger.info("---Token Success");
//            }
//            else throw new RuntimeException("Invalid signature!");
//            filterChain.doFilter(request, response);
//        }
        else throw new RuntimeException("Token is not valid!");
    }
}
