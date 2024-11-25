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

import com.japhni81.jani.models.ItemToPurchase;
import com.japhni81.jani.services.ItemToPurchaseService;

@RestController
public class ItemToPurchaseController {

	@Autowired
	ItemToPurchaseService itemPurchasedService;

	@PostMapping("/create-itemToPurchase")
	public ResponseEntity<ItemToPurchase> saveItemPurchased(@RequestBody ItemToPurchase itemPurchased) {

		return new ResponseEntity<ItemToPurchase>(itemPurchasedService.createItemPurchased(itemPurchased),
				HttpStatus.CREATED);
	}

	@GetMapping("/all-itemPurchased")
	public ResponseEntity<List<ItemToPurchase>> getAllItemPurchaseds() {

		return new ResponseEntity<List<ItemToPurchase>>(itemPurchasedService.getAllItemPurchased(), HttpStatus.OK);
	}

	@GetMapping("/read-itemPurchased-by-id/{id}")
	public ResponseEntity<ItemToPurchase> getItemPurchasedById(@PathVariable Long id) {

		return new ResponseEntity<ItemToPurchase>(itemPurchasedService.getItemPurchasedById(id), HttpStatus.OK);
	}

	@PutMapping("/update-itemPurchased/{id}")
	public ResponseEntity<ItemToPurchase> updateItemPurchased(@PathVariable Long id,
			@RequestBody ItemToPurchase itemPurchased) {

		itemPurchased.setId(id);

		return new ResponseEntity<ItemToPurchase>(itemPurchasedService.createItemPurchased(itemPurchased),
				HttpStatus.OK);
	}

	@DeleteMapping("/delete-itemPurchased/{id}")
	public ResponseEntity<HttpStatus> deleteItemPurchased(@PathVariable Long id) {

		itemPurchasedService.deleteItemPurchased(id);

		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

	}

}
