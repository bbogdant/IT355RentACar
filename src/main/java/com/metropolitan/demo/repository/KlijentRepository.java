package com.metropolitan.demo.repository;

import com.metropolitan.demo.entity.Klijent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface KlijentRepository extends JpaRepository<Klijent, Integer> {

    Optional<Klijent> findByIme(String ime);


}
