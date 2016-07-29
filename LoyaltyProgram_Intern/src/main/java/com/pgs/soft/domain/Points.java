package com.pgs.soft.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "points")
public class Points {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "points_amount")
	private int pointsAmount;

	@Column(name = "date")
	String date;

	@ManyToOne
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;

	@Column(name = "card_number")
	String cardNumber;

	public Points() {

	}

	public Points(int pointsAmount) {
		super();
		this.pointsAmount = pointsAmount;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getPointsAmount() {
		return pointsAmount;
	}

	public void setPointsAmount(int pointsAmount) {
		this.pointsAmount = pointsAmount;
	}
}
