package com.filter;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
 
import java.io.IOException;

import java.io.PrintWriter;

import java.io.StringWriter;
 
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.context.SecurityContext;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.JwtException;

import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.JwtParser;

import jakarta.servlet.FilterChain;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;
 
public class JwtFilterTest {
 
    private JwtFilter jwtFilter;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private FilterChain filterChain;

    private JwtToken jwtToken;

    private JwtParser jwtParser;
 
    @BeforeEach

    public void setUp() {

        jwtFilter = new JwtFilter();

        request = mock(HttpServletRequest.class);

        response = mock(HttpServletResponse.class);

        filterChain = mock(FilterChain.class);

        jwtToken = mock(JwtToken.class);

        jwtParser = mock(JwtParser.class);

    }
 
    @Test

    public void testDoFilterInternalOptionsRequest() throws ServletException, IOException {

        when(request.getMethod()).thenReturn("OPTIONS");
 
        jwtFilter.doFilterInternal(request, response, filterChain);
 
        verify(response).setStatus(HttpServletResponse.SC_OK);

        verify(filterChain, never()).doFilter(request, response);

    }
 
    @Test

    public void testDoFilterInternalLoginRequest() throws ServletException, IOException {

        when(request.getRequestURI()).thenReturn("/api/login");

        when(request.getMethod()).thenReturn("POST");
 
        jwtFilter.doFilterInternal(request, response, filterChain);
 
        verify(filterChain).doFilter(request, response);

    }
 
}
 