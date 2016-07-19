package com.pgs.soft.service;

public interface GenericService<E, D, K> {

	public void saveOrUpdate(D objectDTO);
	public D get(K id);
	public void remove(D objectDTO);

}
