package com.pgs.soft.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.pgs.soft.domain.Card;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.AddCardRequestDTO;

public class CardServiceImplTest {

	CardServiceImpl cardServiceImpl = new CardServiceImpl();

	AddCardRequestDTO cardDTO;

	Card card;

	User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		cardDTO = new AddCardRequestDTO();
		cardDTO.setNumber("123456");

	}

	@Test
	public void testMap() {

		// When
		card = cardServiceImpl.map(cardDTO, user);

		// Then
		assertEquals(cardDTO.getNumber(), card.getNumber());
		assertEquals(user, card.getUser());
		assertEquals(true, card.isActive());

	}

}
