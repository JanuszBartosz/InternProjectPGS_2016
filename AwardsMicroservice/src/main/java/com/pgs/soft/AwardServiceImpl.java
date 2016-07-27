package com.pgs.soft;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Award;
import com.pgs.soft.domain.Category;
import com.pgs.soft.repository.AwardsRepository;

@Service
public class AwardServiceImpl {

	@Autowired
	AwardsRepository awardsRepository;

	@Value("${how.many.awards.generate}")
	private int howManyAwardsGenerate;

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

			awardsRepository.save(award);
		}

	}

	public Set<Award> getAllAwards() {
		return new HashSet<>((Collection<Award>) awardsRepository.findAll());
	}

}