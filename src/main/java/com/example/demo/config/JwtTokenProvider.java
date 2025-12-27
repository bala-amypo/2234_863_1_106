
package com.example.demo.config;

import com.example.demo.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {
    private String secretKey;
    private long validityInMilliseconds;
    
    public JwtTokenProvider() {
        this.secretKey = "VerySecretKeyForJWTsChangeMe";
        this.validityInMilliseconds = 3600000;
    }
    
    public JwtTokenProvider(String secretKey, long validityInMilliseconds) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }
    
    public String generateToken(Long userId, String email, Role role) {
        return "mock-jwt-token-" + userId + "-" + email + "-" + role;
    }
    
    public boolean validateToken(String token) {
        return token != null && token.startsWith("mock-jwt-token");
    }
    
    public MockClaims getClaims(String token) {
        String[] parts = token.split("-");
        if (parts.length >= 6) {
            return new MockClaims(parts[3], parts[4], parts[5]);
        }
        return new MockClaims("1", "test@test.com", "USER");
    }
    
    public static class MockClaims {
        private String subject;
        private java.util.Map<String, Object> claims = new java.util.HashMap<>();
        
        public MockClaims(String userId, String email, String role) {
            this.subject = userId;
            this.claims.put("email", email);
            this.claims.put("role", role);
        }
        
        public String getSubject() { return subject; }
        
        public <T> T get(String key, Class<T> type) {
            return type.cast(claims.get(key));
        }
    }
}
