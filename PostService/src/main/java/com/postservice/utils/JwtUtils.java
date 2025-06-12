package com.postservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class JwtUtils {
    byte[] SECRET_KEY = "mysecretkeymysecretkeymysecretkey123".getBytes();
    
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (List<String>) claims.get("roles");
    }

    private byte[] getSignKey() {
        return this.SECRET_KEY;
    }

    public static boolean requireRole(HttpServletRequest request, JwtUtils jwtUtil, List<String> requiredRole) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No token provided");
        }
        String token = authHeader.substring(7);
        String userRole = jwtUtil.extractRoles(token).getFirst();

        if (userRole == null || !requiredRole.contains(userRole)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient permissions");
        }

        return true;
    }
}
