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
                .antMatchers( "/", "/klijenti/signup","/sortiraj", "/pretraga/**").permitAll()
                .antMatchers(HttpMethod.GET,"/rezervacije").hasAuthority("Admin")
                .antMatchers("/klijenti").hasAuthority("Admin")
                .antMatchers("/vozila/**").hasAuthority("Admin")
                .antMatchers("/transakcije").hasAuthority("Admin")
                .antMatchers("/home").hasAuthority("Admin")


                .antMatchers("/nova-rezervacija/**").hasAuthority("User")


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
                                .logoutSuccessUrl("/")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }


}




