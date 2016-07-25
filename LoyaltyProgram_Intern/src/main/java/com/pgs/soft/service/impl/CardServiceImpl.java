package com.pgs.soft.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Card;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.AddCardNotLoggedRequestDTO;
import com.pgs.soft.dto.AddCardRequestDTO;
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
	public void saveForLogged(AddCardRequestDTO cardLoggedDTO) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOneByEmail(loggedUser.getEmail()).get();
		Card card = map(cardLoggedDTO, user);
		cardRepository.save(card);
	}

	@Override
	public void saveForNotLogged(AddCardNotLoggedRequestDTO cardNotLoggedDTO) {
		User user = userRepository.findOneByEmail(cardNotLoggedDTO.getEmail()).get();
		Card card = map(cardNotLoggedDTO, user);
		cardRepository.save(card);
	}

	public Card map(AddCardRequestDTO cardDTO, User user) {
		Card card = new Card();
		card.setNumber(cardDTO.getNumber());
		card.setUser(user);
		card.setActive(true);
		return card;
	}

	@Override
	public Set<Card> getCardsByUserId(Integer userId) {
		return cardRepository.findByUserId(userId);
	}

	@Override
	public boolean hasActiveCard(Integer userId) {
		return getCardsByUserId(userId).stream().anyMatch((c) -> c.isActive());
	}

	// private void deactivateCards(Integer userId) {
	// Set<Card> cards = cardRepository.findByUserId(userId);
	// cards.stream().forEach((c) -> c.setActive(false));
	// cardRepository.save(cards);
	// }
	//
	// private void activateCard(Integer userId, Integer cardId) {
	// deactivateCards(userId);
	// Card currentCard = cardRepository.findOne(cardId);
	// currentCard.setActive(true);
	// cardRepository.save(currentCard);
	// }
}
