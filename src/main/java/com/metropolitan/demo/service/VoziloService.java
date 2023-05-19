package com.metropolitan.demo.service;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Vozilo;

import java.util.List;

public interface VoziloService {

    List<Vozilo> findAll();

    Vozilo findById(Integer voziloId);

    Vozilo save(Vozilo vozilo);

    Vozilo update(Vozilo vozilo);

    void deleteById(Integer voziloId);
}
