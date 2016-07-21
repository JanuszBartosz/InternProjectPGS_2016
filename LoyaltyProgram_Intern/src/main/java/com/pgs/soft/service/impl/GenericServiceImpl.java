package com.pgs.soft.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pgs.soft.service.GenericService;

@Service
public abstract class GenericServiceImpl<E, D, K extends Serializable> implements GenericService<D, K> {

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
	public List<D> getAll() {
		return StreamSupport.stream(getCrudRepository().findAll().spliterator(), false)
				.map(e -> mapEntityToDto(e))
				.collect(Collectors.toList());
	}

	@Override
	public void remove(K id) {
		getCrudRepository().delete(id);
	}
}
