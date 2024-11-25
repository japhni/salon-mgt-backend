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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debt_paid")
public class DebtPaid {

	@Id
	@Column(name = "debt_paid_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "amount_paid")
	private float amountPaid;

	@Column(name = "paid_date")
	private Date paidDate;

	private String details;

	@ManyToOne
	@JoinColumn(name = "refunder_id", insertable = false, updatable = false)
	private User refunder;
	private Long refunder_id;
	
	@ManyToOne
	@JoinColumn(name = "responsible_id", insertable = false, updatable = false)
	private User responsible;
	private Long responsible_id;
	
}
