package com.pgs.soft.dto;

public abstract class GenericDTO<K> {

	private K id;

	public K getId() {
		return id;
	}

	public void setId(K id) {
		this.id = id;
	}
	
	
	
}
