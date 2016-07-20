package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Card;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.CardDTO;
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
	public void save(CardDTO cardDTO) {
		Card card = new Card();
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOneByEmail(loggedUser.getEmail()).get();
		card.setNumber(cardDTO.getNumber());
		card.setUser(user);
	}
}
