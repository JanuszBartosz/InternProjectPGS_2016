package com.pgs.soft.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.pgs.soft.domain.Award;
import com.pgs.soft.domain.Category;
import com.pgs.soft.dto.AwardDTO;

@RunWith(MockitoJUnitRunner.class)
public class AwardsServiceImplTest {

	AwardServiceImpl awardServiceImpl = new AwardServiceImpl();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testMapEntityToDto() {
		// Given
		String name = "Name";
		String description = "This is description.";
		int pointsPrice = 1000;
		Category category = Category.Books;

		Award award = new Award();
		award.setName(name);
		award.setDescription(description);
		award.setPointsPrice(pointsPrice);
		award.setCategory(category);

		// When
		AwardDTO awardDTO = awardServiceImpl.mapEntityToDto(award);

		// Then
		assertEquals(name, awardDTO.getName());
		assertEquals(description, awardDTO.getDescription());
		assertEquals(pointsPrice, awardDTO.getPointsPrice());
		assertEquals(category, awardDTO.getCategory());
	}
}
