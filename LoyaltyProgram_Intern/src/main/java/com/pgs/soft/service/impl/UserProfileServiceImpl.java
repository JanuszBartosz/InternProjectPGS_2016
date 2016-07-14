package com.pgs.soft.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Hobby;
import com.pgs.soft.domain.User;
import com.pgs.soft.domain.UserProfile;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.repository.HobbyRepository;
import com.pgs.soft.repository.UserProfileRepository;
import com.pgs.soft.repository.UserRepository;
import com.pgs.soft.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	UserRepository userRepository;
	UserProfileRepository userProfileRepository;
	HobbyRepository hobbyRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository, HobbyRepository hobbyRepository) {
		this.userProfileRepository = userProfileRepository;
		this.userRepository = userRepository;
		this.hobbyRepository = hobbyRepository;
	}

	@Override
	public void fill(UserProfileDTO userProfileDTO, String userEmail) {
		User user = userRepository.findOneByEmail(userEmail).get();
		UserProfile userProfile = user.getUserProfile();
		userProfile.setName(userProfileDTO.getName());
		userProfile.setSurname(userProfileDTO.getSurname());
		userProfile.setCity(userProfileDTO.getCity());
		userProfile.setStreet(userProfileDTO.getStreet());
		userProfile.setHomeNumber(userProfileDTO.getHomeNumber());
		userProfile.setPostCode(userProfileDTO.getPostCode());		
		
		for(String hobby : userProfileDTO.getHobbies()){
			Optional<Hobby> optionalHobby = hobbyRepository.findOneByHobbyName(hobby);
			optionalHobby.ifPresent(h -> userProfile.getHobbies().add(h));
		}
		
		userProfileRepository.save(userProfile);
	}

}
