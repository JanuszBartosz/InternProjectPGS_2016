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
	
	@ManyToOne
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;
	
	public Points(){
		
	}
	
	public Points(int pointsAmount) {
		super();
		this.pointsAmount = pointsAmount;
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
}
