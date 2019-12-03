package com.security.complete.security;

import com.security.complete.handler.CustomAccessDeniedHandler;
import com.security.complete.handler.CustomAuthenticationEntryPoint;
import com.security.complete.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Project complete
 * User : suren_v
 * Date : 12/2/2019
 * Time : 4:12 PM
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    public WebSecurity(UserService usersService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = usersService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/user/register").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**")
                .permitAll().anyRequest().authenticated().and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()));
//                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
//                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

}
