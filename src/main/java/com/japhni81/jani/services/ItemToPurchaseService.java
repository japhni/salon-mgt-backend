package com.japhni81.jani.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.japhni81.jani.models.ItemToPurchase;
import com.japhni81.jani.repositories.ItemToPurchaseRepository;

@Service
public class ItemToPurchaseService {

	@Autowired
	ItemToPurchaseRepository itemPurchasedRepository;

	public ItemToPurchase createItemPurchased(ItemToPurchase itemPurchased) {

		return itemPurchasedRepository.save(itemPurchased);
	}

	public List<ItemToPurchase> getAllItemPurchased() {

		return itemPurchasedRepository.findAll();
	}

	public ItemToPurchase getItemPurchasedById(Long id) {

		Optional<ItemToPurchase> itemPurchased = itemPurchasedRepository.findById(id);

		if (itemPurchased.isPresent()) {

			return itemPurchased.get();
		}
		throw new RuntimeException("The ItemPurchased with id %d is not available".formatted(id));
	}

	public void deleteItemPurchased(Long id) {

		itemPurchasedRepository.deleteById(id);
	}

}
