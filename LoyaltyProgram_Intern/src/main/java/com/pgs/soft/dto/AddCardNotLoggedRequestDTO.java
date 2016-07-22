package com.pgs.soft.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class AddCardNotLoggedRequestDTO extends AddCardRequestDTO {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String name; 
	
	@NotEmpty
	private String surname;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
