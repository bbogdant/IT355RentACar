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
                .antMatchers( "/", "/klijenti/signup","/sortiraj", "/pretraga/**", "/detalji", "/kontakt").permitAll()
                .antMatchers(HttpMethod.GET,"/rezervacije").hasAuthority("Admin")
                .antMatchers(HttpMethod.GET,"/klijenti").hasAuthority("Admin")
                .antMatchers(HttpMethod.GET,"/vozila").hasAuthority("Admin")
                .antMatchers(HttpMethod.GET,"/transakcije").hasAuthority("Admin")


                .antMatchers(HttpMethod.GET,"/nova-rezervacija").hasAuthority("User")
                .antMatchers(HttpMethod.POST,"/delete-rezervacija").hasAuthority("User")
                .antMatchers(HttpMethod.GET,"/moje-rezervacije").hasAuthority("User")




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




