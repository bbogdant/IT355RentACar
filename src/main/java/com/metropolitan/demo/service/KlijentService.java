package com.metropolitan.demo.service;

import com.metropolitan.demo.entity.Klijent;
import org.springframework.stereotype.Service;

import java.util.List;



public interface KlijentService {

    List<Klijent> findAll();

    Klijent findById(Integer klijentId);

    Klijent save(Klijent klijent);

    Klijent update(Klijent klijent);

    void deleteById(Integer id);
}
