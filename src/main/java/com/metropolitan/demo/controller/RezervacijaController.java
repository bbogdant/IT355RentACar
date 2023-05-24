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



import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.service.RezervacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RezervacijaController {
	private final RezervacijaService rezervacijaService;

	@GetMapping("/rezervacije")
	public String getAllRezervacije(Model model) {
		List<Rezervacija> rezervacije = rezervacijaService.findAll();
		model.addAttribute("rezervacije", rezervacije);
		return "rezervacija";
	}

//	@GetMapping("/{rezervacijaId}")
//	public String getRezervacijaById(@PathVariable Integer rezervacijaId, Model model) {
//		Rezervacija rezervacija = rezervacijaService.findById(rezervacijaId);
//		model.addAttribute("rezervacija", rezervacija);
//		return "rezervacija/show";
//	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		Rezervacija rezervacija = new Rezervacija();
		model.addAttribute("rezervacija", rezervacija);
		return "rezervacija/create";
	}

	@PostMapping("/new")
	public String createRezervacija(@ModelAttribute("rezervacija") Rezervacija rezervacija) {
		rezervacijaService.save(rezervacija);
		return "redirect:/rezervacije";
	}

	@GetMapping("/{rezervacijaId}/edit")
	public String showEditForm(@PathVariable Integer rezervacijaId, Model model) {
		Rezervacija rezervacija = rezervacijaService.findById(rezervacijaId);
		model.addAttribute("rezervacija", rezervacija);
		return "rezervacija/edit";
	}

	@PostMapping("/{rezervacijaId}/edit")
	public String updateRezervacija(@PathVariable Integer rezervacijaId, @ModelAttribute("rezervacija") Rezervacija rezervacija) {
		rezervacija.setId(rezervacijaId);
		rezervacijaService.update(rezervacija);
		return "redirect:/rezervacije";
	}

	@PostMapping("/{rezervacijaId}/delete")
	public String deleteRezervacija(@PathVariable Integer rezervacijaId) {
		rezervacijaService.deleteById(rezervacijaId);
		return "redirect:/rezervacije";
	}
}

