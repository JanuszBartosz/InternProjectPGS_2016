package com.pgs.soft.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pgs.soft.domain.Hobby;

@Repository
public interface HobbyRepository extends CrudRepository<Hobby, Long> {
	public Optional<Hobby> findOneByHobbyName(String hobbyName);
}
