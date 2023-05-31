package com.metropolitan.demo.security;


import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.repository.KlijentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final KlijentRepository klijentRepository;

    @Override
    public UserDetails loadUserByUsername(String ime) throws UsernameNotFoundException {
        Klijent klijent = klijentRepository.findByIme(ime);

        if (klijent != null) {
            return new User(
                    klijent.getIme(),
                    klijent.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority(klijent.getRole().getImeRole()))
            );
        }
        throw new UsernameNotFoundException("User not found");
    }
}
