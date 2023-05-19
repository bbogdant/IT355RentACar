package com.metropolitan.demo.repository;

import com.metropolitan.demo.entity.Transakcija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransakcijaRepository extends JpaRepository<Transakcija, Integer> {
}
