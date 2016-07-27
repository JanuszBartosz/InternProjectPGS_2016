package com.pgs.soft.dto;

public class MessageDTO {

	private Integer cardId;
	private Integer points;
	private String date;

	public MessageDTO() {
		super();
	}

	public MessageDTO(Integer cardId, Integer points, String date) {
		super();
		this.cardId = cardId;
		this.points = points;
		this.date = date;
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
