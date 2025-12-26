package com.example.demo;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.ComplianceThreshold;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ComplianceThresholdRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.ComplianceThresholdService;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.ComplianceThresholdServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class FullProjectTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ComplianceThresholdRepository thresholdRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    private UserService userService;
    private ComplianceThresholdService thresholdService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Manually injecting dependencies for unit testing the service implementations
        userService = new UserServiceImpl(userRepository, passwordEncoder);
        thresholdService = new ComplianceThresholdServiceImpl(thresholdRepository);
    }

    @Test
    void testUserRegistration() {
        // Test Role as String ("USER") instead of Enum
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setRole("USER"); 

        when(passwordEncoder.encode(anyString())).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registered = userService.register(user);

        assertNotNull(registered);
        assertEquals("USER", registered.getRole());
        assertEquals("test@example.com", registered.getEmail());
    }

    @Test
    void testComplianceThresholdMocking() {
        // Test correct mocking of List return types
        ComplianceThreshold threshold = new ComplianceThreshold("PH", 6.0, 8.0, "LOW", null);

        // ERROR FIX: Use Arrays.asList() or List.of() instead of Optional.of() for methods returning List
        when(thresholdRepository.findAll()).thenReturn(Arrays.asList(threshold));

        List<ComplianceThreshold> results = thresholdService.getAllThresholds();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("PH", results.get(0).getSensorType());
    }

    @Test
    void testComplianceThresholdByType() {
        // Test correct mocking of Optional return types
        ComplianceThreshold threshold = new ComplianceThreshold("TDS", 100.0, 500.0, "MEDIUM", null);

        // findBySensorType returns Optional
        when(thresholdRepository.findBySensorType("TDS")).thenReturn(Optional.of(threshold));

        ComplianceThreshold result = thresholdService.getThresholdBySensorType("TDS");
        
        assertNotNull(result);
        assertEquals("TDS", result.getSensorType());
    }

    @Test
    void testJwtImport() {
        // Simply verifying that the class is available and imported from the correct package
        assertNotNull(JwtTokenProvider.class);
    }
}
