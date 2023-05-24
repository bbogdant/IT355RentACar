package com.metropolitan.demo.controller;


import com.metropolitan.demo.entity.Transakcija;
import com.metropolitan.demo.service.TransakcijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransakcijaController {
	private final TransakcijaService transakcijaService;

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


