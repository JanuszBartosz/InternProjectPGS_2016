package com.pgs.soft.service.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Card;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.CardLoggedDTO;
import com.pgs.soft.dto.CardNotLoggedDTO;
import com.pgs.soft.repository.CardRepository;
import com.pgs.soft.repository.UserRepository;
import com.pgs.soft.service.CardService;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveForLogged(CardLoggedDTO cardLoggedDTO) {
		Card card = new Card();
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOneByEmail(loggedUser.getEmail()).get();
		card.setNumber(cardLoggedDTO.getNumber());
		card.setUser(user);
		cardRepository.save(card);
		if(cardLoggedDTO.isActive()){
			activateCard(user.getId(), card.getId());
		}
	}

	@Override
	public void saveForNotLogged(CardNotLoggedDTO cardNotLoggedDTO) {
		Card card = new Card();
		User user = userRepository.findOneByEmail(cardNotLoggedDTO.getEmail()).get();
		card.setNumber(cardNotLoggedDTO.getNumber());
		card.setUser(user);
		cardRepository.save(card);
		if(cardNotLoggedDTO.isActive()){
			activateCard(user.getId(), card.getId());
		}
	}	
	
	private void deactivateCards(Integer userId){
		Set<Card> cards = cardRepository.findByUserId(userId);
		Iterator<Card> iterator = cards.iterator();
		while(iterator.hasNext()){
			Card currentCard = iterator.next();
			currentCard.setActive(false);
			cardRepository.save(currentCard);
		}	
	}
	
	public void activateCard(Integer userId, Integer cardId){
		deactivateCards(userId);
		Card currentCard = cardRepository.findOne(cardId);
		currentCard.setActive(true);
		cardRepository.save(currentCard);
	}
}
