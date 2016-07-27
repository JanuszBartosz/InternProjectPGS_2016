package com.pgs.soft.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

	private Integer cardId;
	private Integer points;
	private String date;

	public Message() {
		super();
	}

	public Message(Integer cardId, Integer points) {
		super();
		this.cardId = cardId;
		this.points = points;

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("E yyyy.MM.dd HH:mm:ss");
		this.date = ft.format(dNow);
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
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
		return "Message{cardId = " + cardId + ", points = " + points + ", date = " + date + "}";
	}
}
