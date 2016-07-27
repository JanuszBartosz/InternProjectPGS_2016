package com.pgs.soft.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pgs.soft.domain.Award;
import com.pgs.soft.domain.Category;

public interface AwardsRepository extends PagingAndSortingRepository<Award, Integer> {

	List<Award> findByCategory(Category category, Sort sort);
}
