package com.pgs.soft.service;

import org.springframework.stereotype.Service;

import com.pgs.soft.dto.CardLoggedDTO;
import com.pgs.soft.dto.CardNotLoggedDTO;

@Service
public interface CardService {
	public void saveForLogged(CardLoggedDTO cardLoggedDTO);
	public void saveForNotLogged(CardNotLoggedDTO cardNotLoggedDTO);
}
