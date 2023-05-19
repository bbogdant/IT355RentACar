package com.metropolitan.demo.service.impl;

import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.repository.RezervacijaRepository;
import com.metropolitan.demo.service.RezervacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor

public class RezervacijaServiceImpl implements RezervacijaService {


    private final RezervacijaRepository rezervacijaRepository;

    @Override
    public List<Rezervacija> findAll() {
        return rezervacijaRepository.findAll();
    }

    @Override
    public Rezervacija findById(Integer rezervacijaId) {
        return rezervacijaRepository.findById(rezervacijaId)
                .orElseThrow(() -> new NoSuchElementException("RezervacijaService.notFound"));
    }

    @Override
    public Rezervacija save(Rezervacija rezervacija) {
        return null;
    }

    @Override
    public Rezervacija update(Rezervacija rezervacija) {
        return null;
    }

    @Override
    public void deleteById(Integer rezervacijaId) {

    }
}
