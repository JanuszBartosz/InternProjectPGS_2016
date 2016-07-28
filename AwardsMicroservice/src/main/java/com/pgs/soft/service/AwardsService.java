package com.pgs.soft.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

import com.pgs.soft.domain.Category;
import com.pgs.soft.dto.AwardDTO;

public interface AwardsService {

	public void drawAward();

	public List<AwardDTO> getAwardsByCategoryAndSorted(Category category, Sort sort);

	public Map<Category, List<AwardDTO>> getAllAwardsGrouped();
}
