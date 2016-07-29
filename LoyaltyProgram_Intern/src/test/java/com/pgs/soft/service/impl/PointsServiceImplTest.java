package com.pgs.soft.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pgs.soft.domain.Points;
import com.pgs.soft.dto.NewPointsRequestDTO;

@RunWith(SpringJUnit4ClassRunner.class)
public class PointsServiceImplTest {

	PointsServiceImpl pointsServiceImpl = new PointsServiceImpl();

	@Test
	public void mapDtoToEntityTest() {
		// Given
		String cardNumber = "skj1aa";
		Integer points = 200;
		String date = "2016.10.11 11:45";
		NewPointsRequestDTO newPointsRequestDTO = new NewPointsRequestDTO(cardNumber, points, date);

		// When
		Points pointsEntity = pointsServiceImpl.mapDtoToEntity(newPointsRequestDTO);

		// Then
		assertEquals(cardNumber, pointsEntity.getCardNumber());
		assertEquals(Double.valueOf(points), Double.valueOf(pointsEntity.getPointsAmount()));
		assertEquals(date, pointsEntity.getDate());

	}

	@Test
	public void mapEntityToDto() {
		// Given
		String cardNumber = "skj1aa";
		Integer points = 200;
		String date = "2016.10.11 11:45";
		Points pointsEntity = new Points();
		pointsEntity.setCardNumber(cardNumber);
		pointsEntity.setDate(date);
		pointsEntity.setPointsAmount(points);

		// When
		NewPointsRequestDTO newPointsRequestDTO = pointsServiceImpl.mapEntityToDto(pointsEntity);

		// Then
		assertEquals(cardNumber, newPointsRequestDTO.getCardNumber());
		assertEquals(Double.valueOf(points), Double.valueOf(newPointsRequestDTO.getPoints()));
		assertEquals(date, newPointsRequestDTO.getDate());

	}

}
