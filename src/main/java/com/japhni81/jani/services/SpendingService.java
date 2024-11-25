package com.japhni81.jani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.Spending;
import com.japhni81.jani.repositories.SpendingRepository;

@Service
public class SpendingService {

	@Autowired
	SpendingRepository spendingRepository;

	public Spending createSpending(Spending spending) {

		return spendingRepository.save(spending);
	}

	public List<Spending> getAllSpendings() {

		return spendingRepository.findAll();
	}

	public Spending getSpendingById(Long id) {

		Optional<Spending> spending = spendingRepository.findById(id);

		if (spending.isPresent()) {

			return spending.get();
		}
		throw new RuntimeException("The Spending with id %d is not available".formatted(id));
	}

	public void deleteSpending(Long id) {

		spendingRepository.deleteById(id);
	}

}
