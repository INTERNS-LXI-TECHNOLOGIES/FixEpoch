package com.lxisoft.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(2)
public class UserSecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/testLogin").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') ")
            .and()
            .formLogin()
            .loginPage("/login")
            .failureUrl("/login-error")
            .and()
            .logout()
            .logoutSuccessUrl("/home");
    }
}
