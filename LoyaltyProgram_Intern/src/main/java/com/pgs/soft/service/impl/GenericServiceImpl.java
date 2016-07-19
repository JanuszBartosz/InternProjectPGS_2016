package com.pgs.soft.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pgs.soft.service.GenericService;

@Service
public abstract class GenericServiceImpl<E, D, K extends Serializable> implements GenericService<E, D, K> {

	
	protected abstract CrudRepository<E, K> getCrudRepository();

	protected abstract E mapDtoToEntity(D objectDTO);
	
	protected abstract D mapEntityToDto(E entity);
	
	@Override
	public void saveOrUpdate(D objectDTO) {
		E entity = mapDtoToEntity(objectDTO);
		getCrudRepository().save(entity);
	}

	@Override
	public D get(K id) {
		E entity = getCrudRepository().findOne(id);
		D objectDTO = mapEntityToDto(entity);
		return objectDTO;
	}
	
	@Override
	public List<D> getAll(){
		
		List<D> list = new ArrayList<D>();
		Iterator<E> iterator = getCrudRepository().findAll().iterator();
		while(iterator.hasNext()){
			list.add(mapEntityToDto(iterator.next()));
		}
		return list;		
	}

	@Override
	public void remove(D objectDTO) {
		E entity = mapDtoToEntity(objectDTO);
		getCrudRepository().delete(entity);
	}
}
