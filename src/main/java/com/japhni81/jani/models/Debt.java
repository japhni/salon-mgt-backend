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
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debt")
public class Debt {

	@Id
	@Column(name = "debt_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "debt_date")
	private Date debtDate;

	@Column(name = "debt_amount")
	private float debtAmount;

	private String details;

	@ManyToOne
	@JoinColumn(name = "requester_id", insertable = false, updatable = false)
	private User requester;
	private Long requester_id;
	
	@ManyToOne
	@JoinColumn(name = "responsible_id", insertable = false, updatable = false)
	private User responsible;
	private Long responsible_id;

}
