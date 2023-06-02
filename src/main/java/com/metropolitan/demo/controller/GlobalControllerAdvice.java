package com.metropolitan.demo.controller;


import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.service.KlijentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final KlijentService klijentService;

    // ATTRIBUTE
    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        return klijentService.isUserAdmin();
    }

    @ModelAttribute("isUser")
    public boolean isUser() {
        return klijentService.isUserUser();
    }

    @ModelAttribute("user")
    public Klijent getLoggedInUser() {
        return klijentService.getLoggedInUser();
    }
}
