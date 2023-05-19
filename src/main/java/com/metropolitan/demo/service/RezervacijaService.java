package com.metropolitan.demo.service;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;

import java.util.List;

public interface RezervacijaService {

    List<Rezervacija> findAll();

    Rezervacija findById(Integer rezervacijaId);

    Rezervacija save(Rezervacija rezervacija);

    Rezervacija update(Rezervacija rezervacija);

    void deleteById(Integer rezervacijaId);
}
