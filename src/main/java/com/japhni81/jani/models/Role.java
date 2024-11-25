package com.japhni81.jani.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;

	@NotBlank
	private String name;

	@Column(name = "role_level")
	private Integer roleLevel;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private Collection<User> users = new HashSet<>();
	
	public Role(String name) {
		this.name = name;
	}
	
	public void assignRoleToUser(User user) {
		
		user.getRoles().add(this);
		this.getUsers().add(user);
	}
	
	public void removeUserFromRole(User user) {
		user.getRoles().remove(this);
		this.getUsers().remove(user);
	}
	
	public void removeAllUsersFromRole() {
		if(this.getUsers() != null) {
			List<User> roleUsers = this.getUsers().stream().toList();
			roleUsers.forEach(this:: removeUserFromRole);
		}
	}
	
	public String getName() {
		return name != null ? name : "";
	}
	
}
