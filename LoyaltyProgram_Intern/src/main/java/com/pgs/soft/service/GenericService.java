package com.pgs.soft.service;

import java.util.List;

public interface GenericService<D, K> {

	public void saveOrUpdate(D objectDTO);
	public D get(K id);
	public List<D> getAll();
	public void remove(K id);

}
