package com.japhni81.jani.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.Debt;
import com.japhni81.jani.repositories.DebtRepository;

@Service
public class DebtService {

	@Autowired
	DebtRepository debtRepository;

	public Debt createDebt(Debt debt) {

		return debtRepository.save(debt);
	}

	public List<Debt> getAllDebts() {

		return debtRepository.findAll();
	}

	public Debt getDebtById(Long id) {

		Optional<Debt> debt = debtRepository.findById(id);

		if (debt.isPresent()) {

			return debt.get();
		}
		throw new RuntimeException("The Debt with id %d is not available".formatted(id));
	}

	public void deleteDebt(Long id) {

		debtRepository.deleteById(id);
	}
	
	public List<Debt> getDebtHistoryByUserIdAndDates(Long id,Date startDate, Date endDate){
		
		return debtRepository.findRequesterDebtHistoryByDates(id, startDate, endDate);
	}
	
	public List<Debt> getAllDebtsPaidByRequesterId(Long id){
		
		return debtRepository.findDebtHistoryByRequesterId(id);
	}

}
