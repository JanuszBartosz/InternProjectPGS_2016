package com.pgs.soft.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO extends GenericDTO<Integer> {

	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String passwordRepeated;
	
	private String uuid;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	
	
	
}
