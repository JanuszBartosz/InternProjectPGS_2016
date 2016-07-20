package com.pgs.soft.service;

import org.springframework.stereotype.Service;

import com.pgs.soft.dto.CardDTO;

@Service
public interface CardService {
	public void save(CardDTO cardDTO);
}
