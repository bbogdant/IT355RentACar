package com.metropolitan.demo.service.impl;

import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.repository.VoziloRepository;
import com.metropolitan.demo.service.VoziloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor

public class VoziloServiceImpl implements VoziloService {

    private final VoziloRepository voziloRepository;

    @Override
    public List<Vozilo> findAll() {
        return voziloRepository.findAll();
    }

    @Override
    public Vozilo findById(Integer voziloId) {
        return voziloRepository.findById(voziloId)
                .orElseThrow(() -> new NoSuchElementException("VoziloService.notFound"));
    }

    @Override
    public Vozilo save(Vozilo vozilo) {
        return null;
    }

    @Override
    public Vozilo update(Vozilo vozilo) {
        return null;
    }

    @Override
    public void deleteById(Integer voziloId) {

    }
}
