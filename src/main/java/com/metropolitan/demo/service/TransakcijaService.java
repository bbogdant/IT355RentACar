package com.metropolitan.demo.service;

import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.entity.Transakcija;

import java.util.List;

public interface TransakcijaService {

    List<Transakcija> findAll();

    Transakcija findById(Integer transakcijaId);

    Transakcija save(Transakcija transakcija);

    Transakcija update(Transakcija transakcija);

    void deleteById(Integer transakcijaId);
}
