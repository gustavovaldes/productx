package com.gustavo.spring.lab.product.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("guga").password("guga").roles("ADMIN", "USER", "ACTUATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/category").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/category/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/category/**").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/category/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/category/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/product").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/product/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/product/**").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/product/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/product/**").hasRole("USER")
                .and().csrf().disable();
    }
}
