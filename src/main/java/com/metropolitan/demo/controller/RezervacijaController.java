package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.entity.Transakcija;
import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.service.KlijentService;
import com.metropolitan.demo.service.RezervacijaService;
import com.metropolitan.demo.service.VoziloService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RezervacijaController {
	private final RezervacijaService rezervacijaService;
	private final VoziloService voziloService;

	private final KlijentService klijentService;

	@Autowired
	private HttpServletRequest request;

	@GetMapping("/rezervacije")
	public String getAllRezervacije(Model model) {
		List<Rezervacija> rezervacije = rezervacijaService.findAll();
		model.addAttribute("rezervacije", rezervacije);
		return "rezervacija/rezervacija";
	}

	@GetMapping("/rezervacije/moje-rezervacije")
	public String getMyPurchasesPage(Model model) {
		model.addAttribute("transakcija", new Transakcija());
		model.addAttribute("mojeRezervacije", rezervacijaService.findAllByLoggedInMember());
		return "rezervacija/moje-rezervacije";
	}



	@GetMapping("/rezervacije/nova-rezervacija")
	public String showCreateRezervacijaForm(@RequestParam("voziloId") Integer voziloId, Model model) {
		Vozilo vozilo = voziloId != null ? voziloService.findById(voziloId) : new Vozilo();
		Rezervacija rezervacija = new Rezervacija();
		model.addAttribute("rezervacija", rezervacija);
		model.addAttribute("vozilo", vozilo);

		return "rezervacija/dodaj-rezervaciju";
	}

	@PostMapping("/rezervacije/rezervisi")
	public String addRezervacija(@Valid Rezervacija rezervacija, @RequestParam("voziloId") Integer voziloId, BindingResult result) {

		if (result.hasErrors()) {
			return "rezervacija/dodaj-rezervaciju";
		}


		rezervacija.setKlijent(klijentService.getLoggedInUser());
		rezervacija.setVozilo(voziloService.findById(voziloId));
		rezervacija.setDatumRezervacije(LocalDate.from(LocalDateTime.now()));
		rezervacija.setPlaceno(false);
		rezervacijaService.save(rezervacija);
		return "redirect:/";
	}




	@GetMapping("rezervacije/edit-rezervacija/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Rezervacija rezervacija = rezervacijaService.findById(id);
		model.addAttribute("rezervacija", rezervacija);
		return "rezervacija/update-rezervacija";
	}

	@PostMapping("klijenti/update-rezervacija/{id}")
	public String updateRezervacija(@PathVariable("id") Integer id, @Valid Rezervacija rezervacija, BindingResult result, Model model) {
		if (result.hasErrors()) {
			rezervacija.setId(id);
			return "rezervacija/update-rezervacija";
		}

		rezervacijaService.save(rezervacija);
		model.addAttribute("rezervacije", rezervacijaService.findAll());
		return "redirect:/rezervacija";
	}

	@PostMapping("/rezervacije/delete-rezervacija/{rezervacijaId}")
	public String deleteRezervacija(@PathVariable Integer rezervacijaId) {
		rezervacijaService.deleteById(rezervacijaId);
		return "redirect:/";
	}
}

