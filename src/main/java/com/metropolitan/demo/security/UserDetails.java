package com.metropolitan.demo.security;


import com.metropolitan.demo.entity.Klijent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

    private final Klijent klijent;

    public UserDetails(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(klijent.getRole().getImeRole()));
    }

    @Override
    public String getPassword() {
        return klijent.getPassword();
    }

    @Override
    public String getUsername() {
        return klijent.getIme();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//    @Override
//    public boolean isEnabled() {
//        return klijent.isEnabled();
//    }
}
