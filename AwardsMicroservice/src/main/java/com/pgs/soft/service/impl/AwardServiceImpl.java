package com.pgs.soft.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Award;
import com.pgs.soft.domain.Category;
import com.pgs.soft.dto.AwardDTO;
import com.pgs.soft.repository.AwardsRepository;
import com.pgs.soft.service.AwardsService;

@Service
public class AwardServiceImpl implements AwardsService {

	@Autowired
	AwardsRepository awardsRepository;

	@Value("${how.many.awards.generate}")
	private int howManyAwardsGenerate;

	@Override
	@Scheduled(cron = "0 0 0 * * *")
	public void drawAward() {
		Award award;
		Random generator;

		for (int i = 0; i < howManyAwardsGenerate; i++) {
			award = new Award();
			generator = new Random();

			award.setName(RandomStringUtils.randomAlphabetic(10));
			award.setDescription(RandomStringUtils.randomAlphabetic(50));
			award.setPointsPrice(generator.nextInt(1900) + 100);
			award.setCategory(Category.values()[generator.nextInt(Category.values().length)]);
			award.setStockAmount(generator.nextInt(10) + 1);

			awardsRepository.save(award);
		}

	}

	@Override
	public List<AwardDTO> getAwardsByCategoryAndSorted(Category category, Sort sort) {
		return awardsRepository.findByCategory(category, sort).stream().map((a) -> mapEntityToDto(a))
				.collect(Collectors.toList());
	}

	public AwardDTO mapEntityToDto(Award award) {
		AwardDTO awardDTO = new AwardDTO();

		awardDTO.setName(award.getName());
		awardDTO.setDescription(award.getDescription());
		awardDTO.setCategory(award.getCategory());
		awardDTO.setPointsPrice(award.getPointsPrice());
		awardDTO.setStockAmount(award.getStockAmount());

		return awardDTO;
	}

	@Override
	public List<AwardDTO> getAllAwards(Sort sort) {
		return StreamSupport.stream(awardsRepository.findAll(sort).spliterator(), false).map((a) -> mapEntityToDto(a))
				.collect(Collectors.toList());
	}

	@Override
	public Map<Category, List<AwardDTO>> getAllAwardsGrouped(Sort sort) {
		Map<Category, List<AwardDTO>> awardsGrouped = getAllAwards(sort).stream()
				.collect(Collectors.groupingBy(AwardDTO::getCategory));
		return awardsGrouped;
	}
}