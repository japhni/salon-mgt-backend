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

import com.japhni81.jani.models.DebtPaid;
import com.japhni81.jani.services.DebtPaidService;

@RestController
public class DebtPaidController {

	@Autowired
	DebtPaidService debtPaidService;

	@PostMapping("/create-debt-paid")
	public ResponseEntity<DebtPaid> savePaidDebt(@RequestBody DebtPaid paidDebt) {

		return new ResponseEntity<DebtPaid>(debtPaidService.createPaidDebt(paidDebt), HttpStatus.CREATED);
	}

	@GetMapping("/all-debts-paid")
	public ResponseEntity<List<DebtPaid>> getAllPaidDebts() {

		return new ResponseEntity<List<DebtPaid>>(debtPaidService.getAllPaidDebts(), HttpStatus.OK);
	}

	@GetMapping("/read-debt-paid-by-id/{id}")
	public ResponseEntity<DebtPaid> getPaidDebtById(@PathVariable Long id) {

		return new ResponseEntity<DebtPaid>(debtPaidService.getPaidDebtById(id), HttpStatus.OK);
	}

	@PutMapping("/update-debt-paid/{id}")
	public ResponseEntity<DebtPaid> updatePaidDebt(@PathVariable Long id, @RequestBody DebtPaid paidDebt) {

		paidDebt.setId(id);

		return new ResponseEntity<DebtPaid>(debtPaidService.createPaidDebt(paidDebt), HttpStatus.OK);
	}

	@DeleteMapping("/delete-debt-paid/{id}")
	public ResponseEntity<HttpStatus> deletePaidDebt(@PathVariable Long id) {

		debtPaidService.deletePaidDebt(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}
	
	
	@GetMapping("/user-refund-debt-history-by-dates")
    public ResponseEntity<List<DebtPaid>> getHistoryUserById(
    		@RequestParam("userId") Long userId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date endDate
            ) {
	 
	 List<DebtPaid> debtPaid= debtPaidService.getRefunderDebtHistoryByDates(userId,startDate, endDate);
	 
	 return ResponseEntity.ok(debtPaid);
 }
	
	@GetMapping("/all-debts-paid-history-by-refunder-id")
    public ResponseEntity<List<DebtPaid>> getDebtHistoryByRefunderId(@RequestParam("refunderId") Long refunderId) {
	 
	 List<DebtPaid> debtPaid= debtPaidService.getAllDebtsPaidByRefunderId(refunderId);
	 
	 return ResponseEntity.ok(debtPaid);
 }
}
