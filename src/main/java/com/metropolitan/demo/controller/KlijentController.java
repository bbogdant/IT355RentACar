package com.metropolitan.demo.controller;



import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.repository.KlijentRepository;
import com.metropolitan.demo.service.KlijentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class KlijentController {
	private final KlijentService klijentService;


	@GetMapping("/klijenti")
	public String getAllKlijents(Model model) {
		List<Klijent> klijents = klijentService.findAll();
		model.addAttribute("klijents", klijents);
		return "klijent/klijenti";
	}

//	@GetMapping("/{klijentId}")
//	public String getKlijentById(@PathVariable Integer klijentId, Model model) {
//		Klijent klijent = klijentService.findById(klijentId);
//		model.addAttribute("klijent", klijent);
//		return "klijent/klijenti";
//	}

	@GetMapping("klijenti/signup")
	public String showSignUpForm(Model model) {
		Klijent klijent = new Klijent();
		model.addAttribute("klijent", klijent);
		return "klijent/add-klijent";
	}

	@PostMapping("klijenti/add")
	public String addKlijent(@Valid Klijent klijent, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "klijent/add-klijent";
		}

		klijentService.save(klijent);
		return "redirect:/klijenti";
	}

	@PostMapping("klijenti/saveKlijent")
	public String saveKlijent(@RequestBody Klijent klijent) {
		Klijent savedKlijent = klijentService.save(klijent);

		return "redirect:/klijents";
	}

	@GetMapping("klijenti/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Klijent klijent = klijentService.findById(id);
		model.addAttribute("klijent", klijent);
		return "klijent/update-klijent";
	}

	@PostMapping("klijenti/update/{id}")
	public String updateKlijent(@PathVariable("id") Integer id, @Valid Klijent klijent, BindingResult result, Model model) {
		if (result.hasErrors()) {
			klijent.setId(id);
			return "klijent/update-klijent";
		}

		klijentService.save(klijent);
		model.addAttribute("klijenti", klijentService.findAll());
		return "redirect:/klijenti";
	}



	@GetMapping("klijenti/delete/{id}")
	public String deleteKlijentById(@PathVariable Integer id) {
		klijentService.deleteById(id);

		return "redirect:/klijenti";
	}
}
