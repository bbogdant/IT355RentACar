package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Klijent;
import com.metropolitan.demo.service.KlijentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/klijents")
@RequiredArgsConstructor
public class KlijentController {
	private final KlijentService klijentService;

	@GetMapping
	public ResponseEntity<List<Klijent>> getAllKlijents() {
		return ResponseEntity.ok(klijentService.findAll());
	}

	@GetMapping("/{klijentId}")
	public ResponseEntity<Klijent> getKlijentById(@PathVariable Integer klijentId) {
		return ResponseEntity.ok(klijentService.findById(klijentId));
	}

	@PostMapping
	public ResponseEntity<Klijent> saveKlijent(@RequestBody Klijent klijent) {
		return ResponseEntity.status(HttpStatus.CREATED).body(klijentService.save(klijent));
	}

	@PutMapping
	public ResponseEntity<Klijent> updateKlijent(@RequestBody Klijent klijent) {
		return ResponseEntity.ok(klijentService.update(klijent));
	}

	@DeleteMapping("/{klijentId}")
	public void deleteKlijentById(@PathVariable Integer klijentId) {
		klijentService.deleteById(klijentId);
	}

}

