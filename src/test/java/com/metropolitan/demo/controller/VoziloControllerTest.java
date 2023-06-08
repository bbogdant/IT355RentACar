package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.service.VoziloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VoziloControllerTest {

    @Mock
    private VoziloService voziloService;

    @InjectMocks
    private VoziloController voziloController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllVozila() {

        List<Vozilo> mockVozila = Arrays.asList(new Vozilo(), new Vozilo());
        when(voziloService.findAll()).thenReturn(mockVozila);
        Model model = mock(Model.class);


        String result = voziloController.getAllVozila(model);


        assertEquals("vozilo/vozila", result);
        verify(voziloService, times(1)).findAll();
        verify(model, times(1)).addAttribute("vozila", mockVozila);
    }




    @Test
    void testAddVozilo() {

        Vozilo vozilo = new Vozilo();
        BindingResult bindingResult = mock(BindingResult.class);



        String result = voziloController.addVozilo(vozilo, bindingResult);


        assertEquals("redirect:/vozila", result);
        verify(voziloService, times(1)).save(vozilo);

    }


    @Test
    void saveVozila() {

        Vozilo vozilo = new Vozilo();
        BindingResult bindingResult = mock(BindingResult.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);


        String result = voziloController.saveVozila(vozilo);


        verify(voziloService, times(1)).save(vozilo);
        assertEquals("redirect:/vozila", result);
    }



    @Test
    void testDeleteVozilotById() {

        int id = 1;

        String result = voziloController.deleteVozilotById(id);

        assertEquals("redirect:/vozila", result);
        verify(voziloService, times(1)).deleteById(id);
    }
}
