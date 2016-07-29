package com.pgs.soft.dto;

import com.pgs.soft.domain.Category;

public class AwardDTO {

	private String name;

	private String description;

	private int pointsPrice;

	private Category category;

	private int stockAmount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPointsPrice() {
		return pointsPrice;
	}

	public void setPointsPrice(int pointsPrice) {
		this.pointsPrice = pointsPrice;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}

}
