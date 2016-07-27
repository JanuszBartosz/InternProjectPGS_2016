package com.pgs.soft.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.pgs.soft.dto.AwardDTO;

public interface AwardsService {

	public void drawAward();

	public List<AwardDTO> getAwardsByCategoryAndSorted(String category, Sort sort);
}
