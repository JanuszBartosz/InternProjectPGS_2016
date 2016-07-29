package com.pgs.soft.dto;

public class OrderDTO {

	private String name;

	private String surname;

	private String city;

	private String street;

	private String homeNumber;

	private String postCode;

	private String awardName;

	private String description;

	private String pointsPrice;

	private String category;

	public OrderDTO() {
		super();
	}

	public OrderDTO(String name, String surname, String city, String street, String homeNumber, String postCode,
			String awardName, String description, String pointsPrice, String category) {
		super();
		this.name = name;
		this.surname = surname;
		this.city = city;
		this.street = street;
		this.homeNumber = homeNumber;
		this.postCode = postCode;
		this.awardName = awardName;
		this.description = description;
		this.pointsPrice = pointsPrice;
		this.category = category;
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

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPointsPrice() {
		return pointsPrice;
	}

	public void setPointsPrice(String pointsPrice) {
		this.pointsPrice = pointsPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
