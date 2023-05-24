package com.metropolitan.demo.service.impl;

import com.metropolitan.demo.entity.Klijent;

import com.metropolitan.demo.repository.KlijentRepository;
import com.metropolitan.demo.service.KlijentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor

public class KlijentServiceImpl implements KlijentService {

    private final KlijentRepository klijentRepository;


    @Override
    public List<Klijent> findAll() {
        return klijentRepository.findAll();
    }

    @Override
    public Klijent findById(Integer klijentId) {
        return klijentRepository.findById(klijentId)
                .orElseThrow(() -> new NoSuchElementException("KlijentService.notFound"));
    }

    @Override
    public Klijent save(Klijent klijent) {
        return klijentRepository.save(klijent);
    }

    @Override
    public Klijent update(Klijent klijent) {
        return klijentRepository.save(klijent);
    }

    @Override
    public void deleteById(Integer id) {
        klijentRepository.deleteById(id);

    }
}
