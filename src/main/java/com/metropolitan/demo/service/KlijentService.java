package com.metropolitan.demo.service;

import com.metropolitan.demo.entity.Klijent;
import org.springframework.stereotype.Service;

import java.util.List;



public interface KlijentService {

    List<Klijent> findAll();

    Klijent findByIme(String ime);

    Klijent findById(Integer klijentId);

    Klijent getLoggedInUser();

    boolean isUserAdmin();
    boolean isUserUser();

    Klijent save(Klijent klijent);

    Klijent update(Klijent klijent);

    void deleteById(Integer id);
}
