package com.senla.cources.config.security.filters;

import com.senla.cources.service.security.TokenService;
import com.senla.cources.service.security.UserPrincipalService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtBasedAuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenService tokenService;
    private final UserPrincipalService userPrincipalService;

    public JwtBasedAuthorizationFilter(AuthenticationManager authenticationManager, TokenService tokenService, UserPrincipalService userPrincipalService) {
        super(authenticationManager);
        this.tokenService = tokenService;
        this.userPrincipalService = userPrincipalService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        Authentication authentication = null;
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null) {
            String userName = tokenService.getUserNameFromToken(token);
            authentication = new UsernamePasswordAuthenticationToken(userName, null, userPrincipalService.getRolesByUser(userName));
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}