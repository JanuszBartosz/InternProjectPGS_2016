package com.pgs.soft.service;

import java.util.Set;

import com.pgs.soft.domain.Hobby;

public interface HobbyService {
	Set<String> getAllHobbiesNames();

	Set<Hobby> getAllHobbies();
}
