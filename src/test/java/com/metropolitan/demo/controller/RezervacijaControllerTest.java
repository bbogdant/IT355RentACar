package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.entity.Transakcija;
import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.service.KlijentService;
import com.metropolitan.demo.service.RezervacijaService;
import com.metropolitan.demo.service.TransakcijaService;
import com.metropolitan.demo.service.VoziloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class RezervacijaControllerTest {

    @Mock
    private RezervacijaService rezervacijaService;
    @InjectMocks
    private RezervacijaController rezervacijaController;

    @Mock
    private VoziloService voziloService;

    @Mock
    private Model model;

    @Mock
    private KlijentService klijentService;

    @Mock
    private BindingResult bindingResult;




    @Test
    void getAllRezervacije() {

        List<Rezervacija> mockRezervacija = Arrays.asList(new Rezervacija(), new Rezervacija());
        when(rezervacijaService.findAll()).thenReturn(mockRezervacija);
        Model model = mock(Model.class);


        String result = rezervacijaController.getAllRezervacije(model);


        assertEquals("rezervacija/rezervacija", result);
        verify(rezervacijaService, times(1)).findAll();
        verify(model, times(1)).addAttribute("rezervacije", mockRezervacija);
    }

    @Test
    void  getMyPurchasesPage(){




            List<Rezervacija> mockRezervacije = List.of(new Rezervacija(), new Rezervacija());
            when(rezervacijaService.findAllByLoggedInMember()).thenReturn(mockRezervacije);


            String result = rezervacijaController.getMyPurchasesPage(model);


            verify(model).addAttribute("transakcija", new Transakcija());
            verify(model).addAttribute("mojeRezervacije", mockRezervacije);

            assertEquals("rezervacija/moje-rezervacije", result);

    }

    @Test
    void showCreateRezervacijaForm() {


        Integer voziloId = 1;
        Vozilo mockVozilo = new Vozilo();
        Rezervacija mockRezervacija = new Rezervacija();

        when(voziloService.findById(voziloId)).thenReturn(mockVozilo);

        String result = rezervacijaController.showCreateRezervacijaForm(voziloId, model);


        verify(model).addAttribute("rezervacija", mockRezervacija);
        verify(model).addAttribute("vozilo", mockVozilo);

        assertEquals("rezervacija/dodaj-rezervaciju", result);

    }

    @Test
    void addRezervacija() {

        Integer voziloId = 1;
        Rezervacija mockRezervacija = new Rezervacija();

        when(bindingResult.hasErrors()).thenReturn(false);
        when(klijentService.getLoggedInUser()).thenReturn(new Klijent());
        when(voziloService.findById(voziloId)).thenReturn(new Vozilo());


        String result = rezervacijaController.addRezervacija(mockRezervacija, voziloId, bindingResult);


        verify(rezervacijaService).save(mockRezervacija);

        assertEquals("redirect:/", result);
    }

    @Test
    void addRezervacija_withErrors() {

        Integer voziloId = 1;
        Rezervacija mockRezervacija = new Rezervacija();

        when(bindingResult.hasErrors()).thenReturn(true);


        String result = rezervacijaController.addRezervacija(mockRezervacija, voziloId, bindingResult);


        assertEquals("rezervacija/dodaj-rezervaciju", result);
    }

}