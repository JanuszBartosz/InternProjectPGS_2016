package com.pgs.soft.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.mockito.internal.util.collections.Sets;
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
		return getAllHobbies().stream().map((h) -> h.getHobbyName()).collect(Collectors.toSet());
	}

	@Override
	public Set<Hobby> getAllHobbies() {
	//	return new HashSet<Hobby>((Collection<? extends Hobby>) hobbyRepository.findAll());
//		return StreamSupport.stream(hobbyRepository.findAll().spliterator(), false)
//						.collect(Collectors.toSet());
		return new HashSet<>((Collection<Hobby>) hobbyRepository.findAll());
	}
}
