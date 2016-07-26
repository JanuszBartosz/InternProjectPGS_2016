package com.pgs.soft;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Award;
import com.pgs.soft.domain.Category;
import com.pgs.soft.repository.AwardsRepository;

@Service
public class AwardServiceImpl {

	@Autowired
	AwardsRepository awardsRepository;

	@Scheduled(cron = "0 0 0 * * *")
	public void drawAward() {
		Award award = new Award();
		Random generator = new Random();

		award.setName(RandomStringUtils.randomAlphabetic(10));
		award.setDescription(RandomStringUtils.randomAlphabetic(50));
		award.setPointsPrice(generator.nextInt(1900) + 100);
		award.setCategory(Category.values()[generator.nextInt(Category.values().length)]);

		awardsRepository.save(award);
	}
}
