package com.pgs.soft.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

	private String cardNumber;
	private Integer points;
	private String date;

	public Message() {
		super();
	}

	public Message(String cardNumber, Integer points) {
		super();
		this.cardNumber = cardNumber;
		this.points = points;

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd HH:mm:ss");
		this.date = ft.format(dNow);
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
		return "Message{cardId = " + cardNumber + ", points = " + points + ", date = " + date + "}";
	}
}
