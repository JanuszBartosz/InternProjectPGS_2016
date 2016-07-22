package com.pgs.soft.service;

import java.util.Set;

import com.pgs.soft.domain.Card;
import com.pgs.soft.dto.AddCardNotLoggedRequestDTO;
import com.pgs.soft.dto.AddCardRequestDTO;

public interface CardService {
	public void saveForLogged(AddCardRequestDTO cardLoggedDTO);

	public void saveForNotLogged(AddCardNotLoggedRequestDTO cardNotLoggedDTO);

	Set<Card> getCardsByUserId(Integer userId);

	boolean hasActiveCard(Integer userId);
}
