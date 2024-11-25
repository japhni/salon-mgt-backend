package com.japhni81.jani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.CoifStyleType;
import com.japhni81.jani.repositories.CoifStyleTypeRepository;

@Service
public class CoifStyleTypeService {

	@Autowired
	CoifStyleTypeRepository coifStyleTypeRepository;

	public CoifStyleType createCoifStyle(CoifStyleType coifStyleType) {

		return coifStyleTypeRepository.save(coifStyleType);
	}

	public List<CoifStyleType> getAllCoifStyle() {

		return coifStyleTypeRepository.findAll();
	}

	public CoifStyleType getCoifStyleById(Long id) {

		Optional<CoifStyleType> coifStyle = coifStyleTypeRepository.findById(id);

		if (coifStyle.isPresent()) {

			return coifStyle.get();
		}
		throw new RuntimeException("The Coif Style with id %d is not available".formatted(id));
	}

	public void deleteCoifStyle(Long id) {

		coifStyleTypeRepository.deleteById(id);
	}

}
