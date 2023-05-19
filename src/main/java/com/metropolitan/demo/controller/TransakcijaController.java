package com.metropolitan.demo.controller;

import com.metropolitan.demo.entity.Transakcija;
import com.metropolitan.demo.service.TransakcijaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transakcijas")
@RequiredArgsConstructor
public class TransakcijaController {
	private final TransakcijaService transakcijaService;

	@GetMapping
	public ResponseEntity<List<Transakcija>> getAllTransakcijas() {
		return ResponseEntity.ok(transakcijaService.findAll());
	}

	@GetMapping("/{transakcijaId}")
	public ResponseEntity<Transakcija> getTransakcijaById(@PathVariable Integer transakcijaId) {
		return ResponseEntity.ok(transakcijaService.findById(transakcijaId));
	}

	@PostMapping
	public ResponseEntity<Transakcija> saveTransakcija(@RequestBody Transakcija transakcija) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transakcijaService.save(transakcija));
	}

	@PutMapping
	public ResponseEntity<Transakcija> updateTransakcija(@RequestBody Transakcija transakcija) {
		return ResponseEntity.ok(transakcijaService.update(transakcija));
	}

	@DeleteMapping("/{transakcijaId}")
	public void deleteTransakcijaById(@PathVariable Integer transakcijaId) {
		transakcijaService.deleteById(transakcijaId);
	}

}

