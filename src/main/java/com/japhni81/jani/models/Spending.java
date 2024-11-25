package com.japhni81.jani.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "spendings")
public class Spending {
	
	@Id
	@Column(name = "spending_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "spending_date")
	private Date spendingDate;
	
	@Column(name = "unit_price")
	private float unitPrice;
	
	@Column(name = "quantity_purchased")
	private float quantityPurchased;
	
	@Column(name = "amount_paid")
	private float amountPaid;

	private String details;

	@ManyToOne
	@JoinColumn(name = "itemPurchasedId", insertable = false, updatable = false)
	private ItemToPurchase itemPurchased;
	private Long itemPurchasedId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", insertable=false, updatable=false)
	private User user;
	private Long user_id;

	public void setAmountPaid(float x) {
		this.amountPaid = this.unitPrice * this.quantityPurchased;
	}

	
}
