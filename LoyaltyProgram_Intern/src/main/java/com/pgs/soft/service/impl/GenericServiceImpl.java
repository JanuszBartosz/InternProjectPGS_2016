package com.pgs.soft.service.impl;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pgs.soft.dto.GenericDTO;
import com.pgs.soft.service.GenericService;

@Service
public abstract class GenericServiceImpl<E, K extends Serializable> implements GenericService<E, K> {

	
	private CrudRepository<E, K> crudRepository;
			
	public CrudRepository<E, K> getCrudRepository() {
		return crudRepository;
	}

	protected E mapDtoToEntity(GenericDTO<K> objectDTO){
		return null;
	}
	
	protected GenericDTO<K> mapEntityToDto(E entity){
		return null;
	}
	
	@Override
	public void saveOrUpdate(GenericDTO<K> objectDTO) {
		E entity = mapDtoToEntity(objectDTO);
		getCrudRepository().save(entity);
	}

	@Override
	public GenericDTO<K> get(K id) {
		E entity = getCrudRepository().findOne(id);
		GenericDTO<K> objectDTO = mapEntityToDto(entity);
		return objectDTO;
	}

	@Override
	public void remove(GenericDTO<K> objectDTO) {
		E entity = mapDtoToEntity(objectDTO);
		getCrudRepository().delete(entity);
	}
}
