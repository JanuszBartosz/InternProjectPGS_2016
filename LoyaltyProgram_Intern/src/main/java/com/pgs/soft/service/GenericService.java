package com.pgs.soft.service;

import com.pgs.soft.dto.GenericDTO;

public interface GenericService<E, K> {

	public void saveOrUpdate(GenericDTO<K> objectDTO);
	public GenericDTO<K> get(K id);
	public void remove(GenericDTO<K> objectDTO);

}
