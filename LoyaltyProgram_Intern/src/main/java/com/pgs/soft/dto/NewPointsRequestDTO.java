package com.pgs.soft.dto;

public class NewPointsRequestDTO {

	private String cardNumber;
	private Integer points;
	private String date;

	public NewPointsRequestDTO() {
		super();
	}

	public NewPointsRequestDTO(String cardNumber, Integer points, String date) {
		super();
		this.cardNumber = cardNumber;
		this.points = points;
		this.date = date;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message{cardNumber = " + cardNumber + ", points = " + points + ", date = " + date + "}";
	}
}
