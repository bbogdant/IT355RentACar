package com.metropolitan.demo.repository;

import com.metropolitan.demo.entity.Rezervacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {
}
