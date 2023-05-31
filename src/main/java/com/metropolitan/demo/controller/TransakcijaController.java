package com.metropolitan.demo.controller;


import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.entity.Transakcija;
import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.service.RezervacijaService;
import com.metropolitan.demo.service.TransakcijaService;
import javax.validation.Valid;

import com.metropolitan.demo.service.VoziloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransakcijaController {
	private final TransakcijaService transakcijaService;
	private final RezervacijaService rezervacijaService;

	private final VoziloService voziloService;

	@GetMapping("/transakcije")
	public String getAllTransakcije(Model model) {
		List<Transakcija> transakcije = transakcijaService.findAll();
		model.addAttribute("transakcije", transakcije);
		return "transakcija";
	}

//	@GetMapping("/{transakcijaId}")
//	public String getTransakcijaById(@PathVariable Integer transakcijaId, Model model) {
//		Transakcija transakcija = transakcijaService.findById(transakcijaId);
//
//		if (transakcija == null) {
//			return "transakcija-not-found";
//		}
//
//		model.addAttribute("transakcija", transakcija);
//		return "transakcija-details";
//	}

//	@GetMapping("transakcije/plati-form")
//	public String showPlatiForm(Model model) {
//		Transakcija transakcija = new Transakcija();
//		model.addAttribute("transakcija", transakcija);
//		return "transakcija/plati-form";
//	}

	@PostMapping("transakcije/plati")
	public String addTransakcija(@RequestParam("rezervacijaId") Integer rezervacijaId, @RequestParam("voziloId") Integer voziloId, @Valid Transakcija transakcija, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "rezervacija/moje-rezervacije";
		}


		Rezervacija rezervacija = rezervacijaService.findById(rezervacijaId);
		Vozilo vozilo = voziloService.findById(voziloId);

		LocalDate startDate = rezervacija.getPocetniDatum();
		LocalDate endDate = rezervacija.getKrajnjiDatum();

		Duration duration = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay());
		long numberOfDays = duration.toDays();
		BigDecimal ukupnaCena = vozilo.getCenaPoDanu().multiply(BigDecimal.valueOf(numberOfDays));


		transakcija.setRezervacija(rezervacija);
		transakcija.setDatumTransakcije(LocalDate.from(LocalDateTime.now()));
		transakcija.setUkupanIznos(ukupnaCena);

		transakcijaService.save(transakcija);


		return "redirect:/";
	}

	@PostMapping("/transakcija")
	public String saveTransakcija(@ModelAttribute Transakcija transakcija) {
		transakcijaService.save(transakcija);
		return "redirect:/";
	}

	@PostMapping("/transakcija/{transakcijaId}")
	public String updateTransakcija(@PathVariable Integer transakcijaId, @ModelAttribute Transakcija transakcija) {
		Transakcija existingTransakcija = transakcijaService.findById(transakcijaId);

		if (existingTransakcija == null) {
			return "transakcija-not-found";
		}

		existingTransakcija.setUkupanIznos(transakcija.getUkupanIznos());


		transakcijaService.update(existingTransakcija);
		return "redirect:/";
	}

	@PostMapping("/transakcija/{transakcijaId}/delete")
	public String deleteTransakcijaById(@PathVariable Integer transakcijaId) {
		transakcijaService.deleteById(transakcijaId);
		return "redirect:/";
	}
}


