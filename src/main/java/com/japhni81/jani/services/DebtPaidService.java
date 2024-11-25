package com.japhni81.jani.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.DebtPaid;
import com.japhni81.jani.repositories.DebtPaidRepository;

@Service
public class DebtPaidService {

	@Autowired
	DebtPaidRepository debtPaidRepository;

	public DebtPaid createPaidDebt(DebtPaid paidDebt) {

		return debtPaidRepository.save(paidDebt);
	}

	public List<DebtPaid> getAllPaidDebts() {

		return debtPaidRepository.findAll();
	}

	public DebtPaid getPaidDebtById(Long id) {

		Optional<DebtPaid> paidDebt = debtPaidRepository.findById(id);

		if (paidDebt.isPresent()) {

			return paidDebt.get();
		}
		throw new RuntimeException("The DebtPaid with id %d is not available".formatted(id));
	}

	public void deletePaidDebt(Long id) {

		debtPaidRepository.deleteById(id);
	}
	
	public List<DebtPaid> getRefunderDebtHistoryByDates(Long id,Date startDate, Date endDate){
		
		return debtPaidRepository.findRefunderDebtHistoryByDates(id, startDate, endDate);
	}
	
	
	public List<DebtPaid> getAllDebtsPaidByRefunderId(Long id){
		
		return debtPaidRepository.findDebtHistoryByRefunderId(id);
	}

}
