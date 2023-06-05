package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.repository.VoziloRepository;
import com.metropolitan.demo.service.VoziloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    private VoziloService voziloService;

    @Mock
    private VoziloRepository voziloRepository;

    @InjectMocks
    private VoziloController voziloController;

    @InjectMocks
    private IndexController indexController;

    @Mock
    private Model model;

    @Test
    void sortirajPoCeni() {


        List<Vozilo> vozila = new ArrayList<>();

        when(voziloService.findAll()).thenReturn(vozila);
        when(voziloService.sortByPrica(vozila)).thenReturn(vozila);


        String result = indexController.sortirajPoCeni(model);


        verify(model).addAttribute("vozila", vozila);
        assertEquals("index", result);
    }

    @Test
    void pronadjiVozilaPoMarci() {


        String marka = "BMW";
        List<Vozilo> listaVozila = new ArrayList<>();



        when(voziloRepository.findByMarkaIgnoreCase(marka)).thenReturn(listaVozila);


        String result = indexController.pronadjiVozilaPoMarci(marka, model);


        verify(model).addAttribute("listaVozila", listaVozila);
        assertEquals("index", result);
    }

    @Test
    void pronadjiVozilaPoBoji() {


        String boja = "crna";
        List<Vozilo> listaVozila = new ArrayList<>();

        when(voziloRepository.findByBoja(boja)).thenReturn(listaVozila);


        String result = indexController.pronadjiVozilaPoBoji(boja, model);


        verify(model).addAttribute("vozila", listaVozila);
        assertEquals("index", result);
    }

    @Test
    void showLoginForm() {
        String result = indexController.showLoginForm(model);


        assertEquals("/login", result);
    }

    @Test
    void openAdminPanel() {

        String result = indexController.openAdminPanel();


        assertEquals("/kontakt", result);
    }

    @Test
    void prikaziDetaljeVozila() {


        Integer voziloId = 1;
        Vozilo vozilo = new Vozilo();
        when(voziloService.findById(voziloId)).thenReturn(vozilo);


        String result = indexController.prikaziDetaljeVozila(voziloId, model);

        assertEquals("vozilo/detalji", result);
        verify(model).addAttribute("vozilo", vozilo);
    }
}