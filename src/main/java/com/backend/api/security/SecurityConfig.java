package com.backend.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    @Transactional
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        /*http.authorizeRequests()
                .antMatchers( "/api/customers/**").hasAnyRole("Customer")
                .antMatchers( "/api/hospitals/**").hasAnyRole("Hospital")
                .antMatchers( "/api/doctors/**").hasAnyRole("Hospital", "Customer")
                .antMatchers( "/api/plans/**").hasAnyRole("Customer","Hospital")
                .antMatchers( "/api/users/**").hasAnyRole("Employee")
                .antMatchers( "/api/employees").hasAnyRole("Employee")
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .permitAll()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
*/
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
