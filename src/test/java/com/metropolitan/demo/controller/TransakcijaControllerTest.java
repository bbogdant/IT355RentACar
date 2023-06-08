package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.*;
import com.metropolitan.demo.service.TransakcijaService;
import com.metropolitan.demo.service.VoziloService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
class TransakcijaControllerTest {



    @Mock
    private TransakcijaService transakcijaService;
    @InjectMocks
    private TransakcijaController transakcijaController;

    @Test
    void getAllTransakcije() {

        List<Transakcija> mockTransakcija = Arrays.asList(new Transakcija(), new Transakcija());
        when(transakcijaService.findAll()).thenReturn(mockTransakcija);
        Model model = mock(Model.class);


        String result = transakcijaController.getAllTransakcije(model);


        assertEquals("transakcija", result);
        verify(transakcijaService, times(1)).findAll();
        verify(model, times(1)).addAttribute("transakcije", mockTransakcija);
    }


    @Test
    void saveTransakcija() {



        Transakcija mockTransakcija = new Transakcija();


        String result = transakcijaController.saveTransakcija(mockTransakcija);


        verify(transakcijaService).save(mockTransakcija);
        assertEquals("redirect:/", result);
    }


}