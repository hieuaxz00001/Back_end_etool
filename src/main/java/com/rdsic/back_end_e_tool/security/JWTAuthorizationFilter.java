package com.rdsic.back_end_e_tool.security;

import com.rdsic.back_end_e_tool.repository.UserRepository;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Order(1)
public class JWTAuthorizationFilter implements Filter {

    private UserRepository nhanVienRepository;

    private JWTService jwtService;

    public JWTAuthorizationFilter(UserRepository nhanVienRepository, JWTService jwtService) {
        this.nhanVienRepository = nhanVienRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        filterChain.doFilter(request, response);

    }
}
