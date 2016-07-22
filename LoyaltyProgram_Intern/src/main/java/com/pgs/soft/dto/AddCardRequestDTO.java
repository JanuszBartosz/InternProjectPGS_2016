package com.pgs.soft.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class AddCardRequestDTO {
	
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
