package com.metropolitan.demo.repository;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {

    List<Rezervacija> findAllByKlijentId(Integer klijentId);
}
