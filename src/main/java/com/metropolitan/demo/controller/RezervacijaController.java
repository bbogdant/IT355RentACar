package com.metropolitan.demo.controller;
//
//import com.metropolitan.demo.entity.Rezervacija;
//import com.metropolitan.demo.service.RezervacijaService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rezervacijas")
//@RequiredArgsConstructor
//public class RezervacijaController {
//	private final RezervacijaService rezervacijaService;
//
//	@GetMapping
//	public ResponseEntity<List<Rezervacija>> getAllRezervacijas() {
//		return ResponseEntity.ok(rezervacijaService.findAll());
//	}
//
//	@GetMapping("/{rezervacijaId}")
//	public ResponseEntity<Rezervacija> getRezervacijaById(@PathVariable Integer rezervacijaId) {
//		return ResponseEntity.ok(rezervacijaService.findById(rezervacijaId));
//	}
//
//	@PostMapping
//	public ResponseEntity<Rezervacija> saveRezervacija(@RequestBody Rezervacija rezervacija) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(rezervacijaService.save(rezervacija));
//	}
//
//	@PutMapping
//	public ResponseEntity<Rezervacija> updateRezervacija(@RequestBody Rezervacija rezervacija) {
//		return ResponseEntity.ok(rezervacijaService.update(rezervacija));
//	}
//
//	@DeleteMapping("/{rezervacijaId}")
//	public void deleteRezervacijaById(@PathVariable Integer rezervacijaId) {
//		rezervacijaService.deleteById(rezervacijaId);
//	}
//
//}
//



import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.service.RezervacijaService;
import com.metropolitan.demo.service.VoziloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RezervacijaController {
	private final RezervacijaService rezervacijaService;
	private final VoziloService voziloService;

	@GetMapping("/rezervacije")
	public String getAllRezervacije(Model model) {
		List<Rezervacija> rezervacije = rezervacijaService.findAll();
		model.addAttribute("rezervacije", rezervacije);
		return "rezervacija/rezervacija";
	}

	@GetMapping("/rezervacije/{rezervacijaId}")
	public String getRezervacijaById(@PathVariable Integer rezervacijaId, Model model) {
		Rezervacija rezervacijaById = rezervacijaService.findById(rezervacijaId);
		model.addAttribute("rezervacija", rezervacijaById);
		return "rezervacija/rezervisi";
	}

	@GetMapping("rezervacije/nova-rezervacija")
	public String showCreateRezervacijaForm(Model model) {
		List<Vozilo> markeVozila = voziloService.findAll();
		Rezervacija rezervacija = new Rezervacija();
		model.addAttribute("rezervacija", rezervacija);
		model.addAttribute("markeVozila", markeVozila);
		return "rezervacija/dodaj-rezervaciju";
	}

	@PostMapping("rezervacije/rezervisi")
	public String addRezervacija(@Valid Rezervacija rezervacija, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "rezervacija/dodaj-rezervaciju";
		}

		rezervacijaService.save(rezervacija);
		return "redirect:/rezervacije";
	}




//	@GetMapping("/{rezervacijaId}/edit")
//	public String showEditForm(@PathVariable Integer rezervacijaId, Model model) {
//		Rezervacija rezervacija = rezervacijaService.findById(rezervacijaId);
//		model.addAttribute("rezervacija", rezervacija);
//		return "rezervacija/edit";
//	}

//	@PostMapping("/{rezervacijaId}/edit")
//	public String updateRezervacija(@PathVariable Integer rezervacijaId, @ModelAttribute("rezervacija") Rezervacija rezervacija) {
//		rezervacija.setId(rezervacijaId);
//		rezervacijaService.update(rezervacija);
//		return "redirect:/rezervacije";
//	}


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
		return "redirect:/rezervacije";
	}
}

