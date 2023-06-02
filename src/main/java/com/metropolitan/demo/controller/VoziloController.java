package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.repository.VoziloRepository;
import com.metropolitan.demo.service.VoziloService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VoziloController {
	private final VoziloService voziloService;
	private final VoziloRepository voziloRepository;

	@GetMapping("/vozila")
	public String getAllVozila(Model model) {
		List<Vozilo> vozila = voziloService.findAll();
		model.addAttribute("vozila", vozila);
		return "vozilo/vozila";
	}

	@GetMapping("/sva-vozila")
	public String getSvaVozila(Model model) {
		List<Vozilo> vozila = voziloService.findAll();
		model.addAttribute("vozila", vozila);
		return "rezervacija/dodaj-rezervaciju";
	}

//	@GetMapping("/{voziloId}")
//	public String getVozilaById(@PathVariable Integer voziloId, Model model) {
//		Vozilo vozilo = voziloService.findById(voziloId);
//		model.addAttribute("vozilo", vozilo);
//		return "vozila";
//	}

	@GetMapping("/vozila/sortiraj-po-ceni")
	public String sortirajPoCeni(Model model) {
		List<Vozilo> vozila = voziloService.findAll();
		vozila = voziloService.sortByPrica(vozila);
		model.addAttribute("vozila", vozila);
		return "vozilo/vozila";
	}

	@GetMapping("/vozila/search")
	public String pronadjiVozilaPoModelu(@RequestParam("marka") String marka, Model model) {
		List<Vozilo> vozila = (List<Vozilo>) voziloRepository.findByMarkaIgnoreCase(marka);
		model.addAttribute("vozila", vozila);
		return "vozilo/vozila";
	}

	@GetMapping("vozila/new-form")
	public String showForm(Model model) {
		Vozilo vozilo = new Vozilo();
		model.addAttribute("vozilo", vozilo);
		return "vozilo/dodaj-vozilo";
	}

	@PostMapping("vozila/dodaj-vozilo")
	public String addVozilo(@Valid Vozilo vozilo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "vozilo/dodaj-vozilo";
		}

		voziloService.save(vozilo);
		return "redirect:/vozila";
	}

	@PostMapping("/vozila/saveVozilo")
	public String saveVozila(@RequestBody Vozilo vozilo) {
		Vozilo savedVozilo = voziloService.save(vozilo);
		return "redirect:/vozila";
	}

	@GetMapping("/vozila/edit-vozilo/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Vozilo vozilo = voziloService.findById(id);
		model.addAttribute("vozilo", vozilo);
		return "vozilo/update-vozilo";
	}

	@PostMapping("/vozila/update-vozilo/{id}")
	public String updateVozilo(@PathVariable("id") Integer id, @Valid Vozilo vozilo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			vozilo.setId(id);
			return "vozilo/update-vozilo";
		}
		voziloService.save(vozilo);
		model.addAttribute("vozila", voziloService.findAll());
		return "redirect:/vozila";
	}

	@DeleteMapping("/vozila/delete-vozilo/{voziloId}")
	public String deleteVozilotById(@PathVariable Integer voziloId) {
		voziloService.deleteById(voziloId);
		return "redirect:/vozila";
	}
}

