package com.nenadm.JwtProject.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class CustomFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            throw new ServletException("Not authorized 401");
        }

        String jwtToken = "";
        Claims claims;

        for (Cookie c:cookies) {
            if (c.getName().equals("authorization"))
               jwtToken = c.getValue();

        }

        if(jwtToken.equals(""))
            throw new ServletException("Not authorized 401");
        

        try{
            claims = Jwts.parser().setSigningKey("aSf4#f3d#@").parseClaimsJws(jwtToken).getBody();

        }catch (SignatureException e){
            throw new ServletException("Not authorized 401");
        }




        filterChain.doFilter(servletRequest,servletResponse);

    }
}
