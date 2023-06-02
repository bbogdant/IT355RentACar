package com.metropolitan.demo.repository;


import com.metropolitan.demo.entity.Vozilo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoziloRepository extends JpaRepository<Vozilo, Integer> {

    List<Vozilo> findByMarkaIgnoreCase(String marka);




}
