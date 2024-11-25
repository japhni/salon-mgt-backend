package com.japhni81.jani.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Long id;

	@Column(name = "traitement_type")
	private String traitementType;

	@Column(name = "time_booking")
	private Date timeBooking;

	@ManyToOne
	@JoinColumn(name = "customerid", insertable = false, updatable = false)
	private Customer customer;
	private Long customerid;

	@OneToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;
	private Long userid;
	
}
