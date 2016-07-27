package com.pgs.soft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pgs.soft.domain.Points;

@Repository
public interface PointsRepository extends CrudRepository<Points, Long> {

}
