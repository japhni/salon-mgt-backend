package com.japhni81.jani.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "employee_customer")
public class EmployeeCustomer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private float amount;
	
	@Column(name = "employee_part")
	private float employeePart;
	
	@Column(name = "boss_part")
	private float bossPart;

	private boolean payed;
	
	@Column(name = "is_bonus")
	private boolean isBonus;
	
	private boolean motivated;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	
	@ManyToOne
	@JoinColumn(name = "employee_id", insertable=false, updatable=false)
	private Employee employee;
	private Long employee_id;

	@ManyToOne
	@JoinColumn(name = "customer_id", insertable=false, updatable=false)
	private Customer customer;
	private Long customer_id;
	
	@ManyToOne
	@JoinColumn(name = "coif_style_type_id", insertable=false, updatable=false)
	private CoifStyleType coifStyleType;
	private Long coif_style_type_id;
	
}
