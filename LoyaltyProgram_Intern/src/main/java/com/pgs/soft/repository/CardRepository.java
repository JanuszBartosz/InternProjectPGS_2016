package com.pgs.soft.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pgs.soft.domain.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {
	Set<Card> findByUserId(Integer id);
}
