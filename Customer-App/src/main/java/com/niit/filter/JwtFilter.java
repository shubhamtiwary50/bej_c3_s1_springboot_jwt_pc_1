package com.niit.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServeLetResponse= (HttpServletResponse) servletResponse;
        String authHeader=httpServletRequest.getHeader("authorization");
        System.out.println(" AuthHeader = " + authHeader);
        ServletOutputStream servletOutputStream= httpServeLetResponse.getOutputStream();
        if(authHeader==null || !authHeader.startsWith("Bearer")){
            httpServeLetResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("missing or invalid token");
            servletOutputStream.close();

        }else {
            String jwttoken = authHeader.substring(7);
            System.out.println(" JWT Token = "+ jwttoken);
            String username = Jwts.parser().setSigningKey("security key").parseClaimsJwt(jwttoken).getBody().getSubject();
            httpServletRequest.setAttribute("firstName",username);
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}

