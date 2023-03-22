package com.effective.shop.controllers.filter;

import com.effective.shop.service.AuthenticateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class RequestFilter extends OncePerRequestFilter {

    private static final List<String> pathsNotForFilter = List.of("swagger", "api-docs", "webjars", "csrf",
            "/api/v1/user/signUp", "/api/v1/user/signIn");

    AuthenticateService authenticateService;

    public RequestFilter(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            authenticateService.authenticateWithJwt(request);
        } catch (Exception exception) {
            log.error("Exception was detected during filtering request for JWT token", exception);
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        for(String path : pathsNotForFilter){
            if(request.getRequestURI().contains(path)){
                return true;
            }
        }
        return false;
    }
}
