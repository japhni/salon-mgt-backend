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

import com.japhni81.jani.models.CoifStyleType;
import com.japhni81.jani.services.CoifStyleTypeService;

@RestController
public class CoifStyleTypeController {

	@Autowired
	CoifStyleTypeService coifStyleTypeService;

	@PostMapping("/create-coif-style-type")
	public ResponseEntity<CoifStyleType> saveItemPurchased(@RequestBody CoifStyleType coifStyleType) {

		return new ResponseEntity<CoifStyleType>(coifStyleTypeService.createCoifStyle(coifStyleType),
				HttpStatus.CREATED);
	}

	@GetMapping("/all-coif-styles")
	public ResponseEntity<List<CoifStyleType>> getAllCoifStyles() {

		return new ResponseEntity<List<CoifStyleType>>(coifStyleTypeService.getAllCoifStyle(), HttpStatus.OK);
	}

	@GetMapping("/read-coif-style-by-id/{id}")
	public ResponseEntity<CoifStyleType> getItemPurchasedById(@PathVariable Long id) {

		return new ResponseEntity<CoifStyleType>(coifStyleTypeService.getCoifStyleById(id), HttpStatus.OK);
	}

	@PutMapping("/update-coif-style/{id}")
	public ResponseEntity<CoifStyleType> updateItemPurchased(@PathVariable Long id,
			@RequestBody CoifStyleType coifStyleType) {

		coifStyleType.setId(id);

		return new ResponseEntity<CoifStyleType>(coifStyleTypeService.createCoifStyle(coifStyleType),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete-coif-style/{id}")
	public ResponseEntity<HttpStatus> deleteCoifStyle(@PathVariable Long id) {

		coifStyleTypeService.deleteCoifStyle(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}

}
