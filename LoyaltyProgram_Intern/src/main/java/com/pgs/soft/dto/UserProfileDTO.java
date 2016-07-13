package com.pgs.soft.dto;

public class UserProfileDTO {

	private String name;

	private String surname;
	
	private String city;
	
	private String street;
	
	private String homeNumber;
	
	private String postCode;
	
	public UserProfileDTO(){
		name = "";
		surname = "";
		city = "";
		street = "";
		homeNumber = "";
		postCode = "";
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}	
	
	
}
