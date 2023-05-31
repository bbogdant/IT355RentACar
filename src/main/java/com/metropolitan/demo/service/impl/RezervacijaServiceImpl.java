package com.metropolitan.demo.service.impl;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.repository.KlijentRepository;
import com.metropolitan.demo.repository.RezervacijaRepository;
import com.metropolitan.demo.security.SecurityUtil;
import com.metropolitan.demo.service.KlijentService;
import com.metropolitan.demo.service.RezervacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class RezervacijaServiceImpl implements RezervacijaService {


    private final RezervacijaRepository rezervacijaRepository;
    private final KlijentRepository klijentRepository;

    private final KlijentService klijentService;

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

        return rezervacijaRepository.save(rezervacija);
    }

    @Override
    public Rezervacija update(Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    @Override
    public void deleteById(Integer rezervacijaId) {
        rezervacijaRepository.deleteById(rezervacijaId);

    }

    @Override
    public List<Rezervacija> findAllByLoggedInMember() {
        String username = SecurityUtil.getSessionUser();
        Klijent klijent = klijentService.findByIme(username);
        List<Rezervacija> mojeRezervacije = rezervacijaRepository.findAllByKlijentId(klijent.getId());




        return mojeRezervacije;
    }


}
