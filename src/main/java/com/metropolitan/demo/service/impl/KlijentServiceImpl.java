package com.metropolitan.demo.service.impl;

import com.metropolitan.demo.entity.Klijent;

import com.metropolitan.demo.entity.Role;
import com.metropolitan.demo.repository.KlijentRepository;
import com.metropolitan.demo.repository.RoleRepository;

import com.metropolitan.demo.security.SecurityUtil;
import com.metropolitan.demo.service.KlijentService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor

public class KlijentServiceImpl  implements KlijentService {

    private final KlijentRepository klijentRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;




    @Override
    public List<Klijent> findAll() {
        return klijentRepository.findAll();
    }

    @Override
    public Klijent findByIme(String ime) {
        return klijentRepository.findByIme(ime);
    }


    @Override
    public Klijent findById(Integer klijentId) {
        return klijentRepository.findById(klijentId)
                .orElseThrow(() -> new NoSuchElementException("KlijentService.notFound"));
    }

    @Override
    public Klijent save(Klijent klijent) {

        Role roleUser = roleRepository.findByImeRole(Role.USER).orElse(null);
        klijent.setRole(roleUser);

        klijent.setPassword(passwordEncoder.encode(klijent.getPassword()));

        return klijentRepository.save(klijent);
    }
    @Override
    public Klijent getLoggedInUser() {
        String ime = SecurityUtil.getSessionUser();
        return findByIme(ime);
    }


    @Override
    public Klijent update(Klijent klijent) {
        return klijentRepository.save(klijent);
    }

    @Override
    public void deleteById(Integer id) {
        klijentRepository.deleteById(id);

    }

    @Override
    public boolean isUserAdmin() {
        return getLoggedInUser() != null && getLoggedInUser().getRole().getImeRole().equals(Role.ADMIN);
    }
}
