package com.pgs.soft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pgs.soft.domain.Card;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

}
