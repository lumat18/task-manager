package com.spartez.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Collections;
import java.util.Set;

@Slf4j
public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String header = request.getHeader("Authorization");
        if (header != null) {
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationByToken(header);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } else {
            log.warn("Unauthorized request");
            SecurityContextHolder.clearContext();
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(final String header) {
        final String token = header.replace("Bearer ", "");
        UsernamePasswordAuthenticationToken authenticationToken = null;
        try {
            authenticationToken = parseToken(token);
        } catch (Exception exception) {
            log.error("Wrong token: " + token);
        }
        return authenticationToken;
    }

    private UsernamePasswordAuthenticationToken parseToken(final String token) throws JwtException, NullPointerException {
        final Jws<Claims> jws = Jwts.parser().setSigningKey("sampleKey".getBytes()).parseClaimsJws(token);
        final String username = jws.getBody().get("name").toString();
        final Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
}
