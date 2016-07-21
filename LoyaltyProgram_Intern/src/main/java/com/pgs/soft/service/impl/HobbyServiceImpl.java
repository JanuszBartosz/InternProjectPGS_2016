package com.pgs.soft.service.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.soft.repository.HobbyRepository;
import com.pgs.soft.domain.Hobby;
import com.pgs.soft.service.HobbyService;

@Service
public class HobbyServiceImpl implements HobbyService {
	
	@Autowired
	HobbyRepository hobbyRepository;

	@Override
	public Set<String> getAllHobbiesNames() {
		Set<String> hobbiesNames = new HashSet<String>();
		for (Hobby hobby : getAllHobbies()){
			hobbiesNames.add(hobby.getHobbyName());
		}
		return hobbiesNames;
	}

	@Override
	public Set<Hobby> getAllHobbies() {
		return StreamSupport.stream(hobbyRepository.findAll().spliterator(), false)
						.collect(Collectors.toSet());
	}
}
