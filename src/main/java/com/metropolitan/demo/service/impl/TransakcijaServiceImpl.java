package com.metropolitan.demo.service.impl;

import com.metropolitan.demo.entity.Transakcija;
import com.metropolitan.demo.repository.TransakcijaRepository;
import com.metropolitan.demo.service.TransakcijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor

public class TransakcijaServiceImpl implements TransakcijaService {

    private final TransakcijaRepository transakcijaRepository;

    @Override
    public List<Transakcija> findAll() {
        return transakcijaRepository.findAll();
    }

    @Override
    public Transakcija findById(Integer transakcijaId) {
        return transakcijaRepository.findById(transakcijaId)
                .orElseThrow(() -> new NoSuchElementException("TransakcijaService.notFound"));
    }

    @Override
    public Transakcija save(Transakcija transakcija) {
        return transakcijaRepository.save(transakcija);
    }

    @Override
    public Transakcija update(Transakcija transakcija) {
        return transakcijaRepository.save(transakcija);
    }

    @Override
    public void deleteById(Integer transakcijaId) {

    }
}
