package com.pgs.soft.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Hobby;

@Service
public interface HobbyService {
	Set<String> getAllHobbiesNames();
	
	Set<Hobby> getAllHobbies();
}
