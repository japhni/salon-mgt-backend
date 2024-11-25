package com.japhni81.jani.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.japhni81.jani.models.Debt;
import com.japhni81.jani.services.DebtService;

@RestController
public class DebtController {

	@Autowired
	DebtService debtService;

	@PostMapping("/create-debt")
	public ResponseEntity<Debt> saveDebt(@RequestBody Debt debt) {

		return new ResponseEntity<Debt>(debtService.createDebt(debt), HttpStatus.CREATED);
	}

	@GetMapping("/all-debts")
	public ResponseEntity<List<Debt>> getAllDebt() {

		return new ResponseEntity<List<Debt>>(debtService.getAllDebts(), HttpStatus.OK);
	}

	@GetMapping("/read-debt-by-id/{id}")
	public ResponseEntity<Debt> getDebtById(@PathVariable Long id) {

		return new ResponseEntity<Debt>(debtService.getDebtById(id), HttpStatus.OK);
	}

	@PutMapping("/update-debt/{id}")
	public ResponseEntity<Debt> updateDebt(@PathVariable Long id, @RequestBody Debt debt) {

		debt.setId(id);

		return new ResponseEntity<Debt>(debtService.createDebt(debt), HttpStatus.OK);
	}

	@DeleteMapping("/delete-debt/{id}")
	public ResponseEntity<HttpStatus> deleteDebt(@PathVariable Long id) {

		debtService.deleteDebt(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("/user-debt-history-by-dates")
    public ResponseEntity<List<Debt>> getHistoryUserById(
    		@RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date endDate
            ) {
	 
	 List<Debt> debt= debtService.getDebtHistoryByUserIdAndDates(userId,startDate, endDate);
	 
	 return ResponseEntity.ok(debt);
 }
	
	
	@GetMapping("/all-debts-history-by-requester-id")
    public ResponseEntity<List<Debt>> getDebtHistoryByRequesterId(@RequestParam("requesterId") Long requesterId) {
	 
	 List<Debt> debt= debtService.getAllDebtsPaidByRequesterId(requesterId);
	 
	 return ResponseEntity.ok(debt);
 }
	
}
