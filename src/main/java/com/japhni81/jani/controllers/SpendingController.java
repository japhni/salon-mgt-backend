package com.japhni81.jani.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.models.Spending;
import com.japhni81.jani.services.SpendingService;

@RestController
public class SpendingController {

	@Autowired
	SpendingService spendingService;

	@PostMapping("/create-spending")
	public ResponseEntity<Spending> saveSpending(@RequestBody Spending spending) {

		return new ResponseEntity<Spending>(spendingService.createSpending(spending), HttpStatus.CREATED);
	}

	@GetMapping("/all-spendings")
	public ResponseEntity<List<Spending>> getAllSpendings() {

		return new ResponseEntity<List<Spending>>(spendingService.getAllSpendings(), HttpStatus.OK);
	}

	@GetMapping("/read-spending-by-id/{id}")
	public ResponseEntity<Spending> getSpendingById(@PathVariable Long id) {

		return new ResponseEntity<Spending>(spendingService.getSpendingById(id), HttpStatus.OK);
	}

	@PutMapping("/update-spending/{id}")
	public ResponseEntity<Spending> updateSpending(@PathVariable Long id, @RequestBody Spending spending) {

		spending.setId(id);

		return new ResponseEntity<Spending>(spendingService.createSpending(spending), HttpStatus.OK);
	}

	@DeleteMapping("/delete-spending/{id}")
	public ResponseEntity<HttpStatus> deleteSpending(@PathVariable Long id) {

		spendingService.deleteSpending(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}

}
