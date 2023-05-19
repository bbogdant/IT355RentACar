package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Vozilo;
import com.metropolitan.demo.service.VoziloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vozilos")
@RequiredArgsConstructor
public class VoziloController {
	private final VoziloService voziloService;

	@GetMapping
	public ResponseEntity<List<Vozilo>> getAllVozilos() {
		return ResponseEntity.ok(voziloService.findAll());
	}

	@GetMapping("/{voziloId}")
	public ResponseEntity<Vozilo> getVoziloById(@PathVariable Integer voziloId) {
		return ResponseEntity.ok(voziloService.findById(voziloId));
	}

	@PostMapping
	public ResponseEntity<Vozilo> saveVozilo(@RequestBody Vozilo vozilo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(voziloService.save(vozilo));
	}

	@PutMapping
	public ResponseEntity<Vozilo> updateVozilo(@RequestBody Vozilo vozilo) {
		return ResponseEntity.ok(voziloService.update(vozilo));
	}

	@DeleteMapping("/{voziloId}")
	public void deleteVoziloById(@PathVariable Integer voziloId) {
		voziloService.deleteById(voziloId);
	}

}

