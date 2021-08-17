package com.spartez.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .addFilter(new JwtFilter(authenticationManager()));
        http.csrf().disable();
        http.cors();
        http.headers().frameOptions().disable();
    }
}
