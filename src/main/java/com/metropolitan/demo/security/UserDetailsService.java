package com.metropolitan.demo.security;


import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.repository.KlijentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    private final KlijentRepository klijentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Klijent klijent = klijentRepository.findByIme(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));
        return new com.metropolitan.demo.security.UserDetails(klijent);
    }
}
