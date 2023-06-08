package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.service.KlijentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalControllerAdviceTest {

    private KlijentService klijentService;
    private GlobalControllerAdvice globalControllerAdvice;




    @Test
    void isAdmin() {


        KlijentService klijentServiceMock = mock(KlijentService.class);

        when(klijentServiceMock.isUserAdmin()).thenReturn(true);


        GlobalControllerAdvice globalControllerAdvice = new GlobalControllerAdvice(klijentServiceMock);


        boolean isAdmin = globalControllerAdvice.isAdmin();


        assertTrue(isAdmin);
    }

    @BeforeEach
    void setUp() {
        klijentService = mock(KlijentService.class);
        globalControllerAdvice = new GlobalControllerAdvice(klijentService);
    }

    @Test
    void isUser() {
        when(klijentService.isUserUser()).thenReturn(true);
        boolean result = globalControllerAdvice.isUser();
        assertEquals(true, result);
    }

    @Test
    void getLoggedInUser() {
        Klijent expectedUser = new Klijent();
        when(klijentService.getLoggedInUser()).thenReturn(expectedUser);
        Klijent result = globalControllerAdvice.getLoggedInUser();
        assertEquals(expectedUser, result);
    }
}