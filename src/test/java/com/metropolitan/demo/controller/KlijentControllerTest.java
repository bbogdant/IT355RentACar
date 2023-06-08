package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.service.KlijentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KlijentControllerTest {

    @Mock
    private KlijentService klijentService;

    @InjectMocks
    private KlijentController klijentController;

    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;


    @Test
    void getAllKlijents() {


        List<Klijent> mockKlijents = new ArrayList<>();


        when(klijentService.findAll()).thenReturn(mockKlijents);


        String result = klijentController.getAllKlijents(model);



        verify(model).addAttribute("klijents", mockKlijents);
        assertEquals("klijent/klijenti", result);

    }

    @Test
    void addKlijent() {


        Klijent mockKlijent = new Klijent();


        when(bindingResult.hasErrors()).thenReturn(false);


        String result = klijentController.addKlijent(mockKlijent, bindingResult,model);


        verify(klijentService).save(mockKlijent);
        assertEquals("redirect:/", result);
    }

    @Test
    void showUpdateForm() {

        Integer klijentId = 1;
        Klijent mockKlijent = new Klijent();


        when(klijentService.findById(klijentId)).thenReturn(mockKlijent);


        String result = klijentController.showUpdateForm(klijentId, model);


        verify(model).addAttribute("klijent", mockKlijent);
        assertEquals("klijent/update-klijent", result);
    }

    @Test
    void updateKlijent() {


        Integer klijentId = 1;
        Klijent mockKlijent = new Klijent();


        when(klijentService.save(mockKlijent)).thenReturn(mockKlijent);


        String result = klijentController.updateKlijent(klijentId, mockKlijent, bindingResult, model);


        verify(klijentService).save(mockKlijent);
        verify(model).addAttribute("klijenti", klijentService.findAll());
        assertEquals("redirect:/klijenti", result);

    }

    @Test
    void deleteKlijentById() {


        Integer klijentId = 1;


        String result = klijentController.deleteKlijentById(klijentId);


        verify(klijentService).deleteById(klijentId);
        assertEquals("redirect:/klijenti", result);
    }
}