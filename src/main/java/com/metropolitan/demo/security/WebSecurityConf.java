package com.metropolitan.demo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConf {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/", "/klijenti/signup").permitAll()
                .antMatchers(HttpMethod.GET,"/rezervacije").hasRole("Admin")
                .antMatchers("/klijenti").hasRole("Admin")
                .antMatchers("/vozila/**").hasRole("Admin")
                .antMatchers("/transakcije").hasRole("Admin")
                .antMatchers(HttpMethod.GET,"/home").hasRole("Admin")


                .antMatchers("/nova-rezervacija").hasRole("User")

                .anyRequest().authenticated()
                .and()
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error")
                                .permitAll()
                                .and()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }


}




