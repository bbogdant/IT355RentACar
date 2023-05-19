package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Rezervacija;
import com.metropolitan.demo.service.RezervacijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rezervacijas")
@RequiredArgsConstructor
public class RezervacijaController {
	private final RezervacijaService rezervacijaService;

	@GetMapping
	public ResponseEntity<List<Rezervacija>> getAllRezervacijas() {
		return ResponseEntity.ok(rezervacijaService.findAll());
	}

	@GetMapping("/{rezervacijaId}")
	public ResponseEntity<Rezervacija> getRezervacijaById(@PathVariable Integer rezervacijaId) {
		return ResponseEntity.ok(rezervacijaService.findById(rezervacijaId));
	}

	@PostMapping
	public ResponseEntity<Rezervacija> saveRezervacija(@RequestBody Rezervacija rezervacija) {
		return ResponseEntity.status(HttpStatus.CREATED).body(rezervacijaService.save(rezervacija));
	}

	@PutMapping
	public ResponseEntity<Rezervacija> updateRezervacija(@RequestBody Rezervacija rezervacija) {
		return ResponseEntity.ok(rezervacijaService.update(rezervacija));
	}

	@DeleteMapping("/{rezervacijaId}")
	public void deleteRezervacijaById(@PathVariable Integer rezervacijaId) {
		rezervacijaService.deleteById(rezervacijaId);
	}

}

