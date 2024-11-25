package com.japhni81.jani.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String password;

	private String firstName;

	private String lastName;

	private String otherNames;

	private LocalDate birthday;

	@Column(name = "phone_number")
	private String phoneNumber;

	private String email;
	
	@Column(name = "first_address")
	private String firstAddress;
	
	@Column(name = "second_address")
	private String secondAdress;
	
	private String details;
	
	private boolean authorized;

	@Column(name = "account_not_expired")
	private boolean accountNotExpired;
	
	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "account_not_locked")
	private boolean accountNotLocked;

	@Column(name = "one_time_password")
	private String oneTimePassword;

	@Column(name = "otp_requested_time")
	private Date otpRequestedTime;

	@Column(name = "lock_time")
	private Date lockTime;

	@Column(name = "reset_password_token")
	private String resetPasswordToken;

	@Column(name = "password_change_time")
	private Date passwordChangeTime;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
	private Collection<Role> roles = new HashSet<>();
	
	public void addRoles(Role role) {
		this.roles.add(role);
	}

	public User(String firstName, String lastName, String otherNames, LocalDate birthday, String phoneNumber,
			String email, String firstAddress, String secondAdress, String details, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.otherNames = otherNames;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.firstAddress = firstAddress;
		this.secondAdress = secondAdress;
		this.details = details;
		this.roles = roles;
	}
	
	
	
	
	
}
