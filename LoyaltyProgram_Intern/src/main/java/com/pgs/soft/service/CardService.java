package com.pgs.soft.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Card;
import com.pgs.soft.dto.CardLoggedDTO;
import com.pgs.soft.dto.CardNotLoggedDTO;

@Service
public interface CardService {
	public void saveForLogged(CardLoggedDTO cardLoggedDTO);
	public void saveForNotLogged(CardNotLoggedDTO cardNotLoggedDTO);
	Set<Card> getCardsByUserId(Integer userId);
	boolean hasActiveCard(Integer userId);
}
