package com.spartez.configuration;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("request = " + request);
        final String header = request.getHeader("Authorization");
        if(header != null){
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationByToken(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(final String header) {
        final String token = header.replace("Bearer ", "");
        final Jws<Claims> jws = Jwts.parser().setSigningKey("sampleKey".getBytes()).parseClaimsJws(token);
        final String username = jws.getBody().get("name").toString();
        final String role = jws.getBody().get("role").toString();
        final Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(role));

        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
