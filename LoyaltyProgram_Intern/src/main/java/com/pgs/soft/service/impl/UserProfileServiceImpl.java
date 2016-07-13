package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.User;
import com.pgs.soft.domain.UserProfile;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.repository.UserProfileRepository;
import com.pgs.soft.repository.UserRepository;
import com.pgs.soft.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	UserRepository userRepository;
	UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository) {
		this.userProfileRepository = userProfileRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void fill(UserProfileDTO userProfileDTO, String userEmail) {
		User user = userRepository.findUserByEmail(userEmail);
		UserProfile userProfile = user.getUserProfile();
		userProfile.setName(userProfileDTO.getName());
		userProfile.setSurname(userProfileDTO.getSurname());
		userProfile.setCity(userProfileDTO.getCity());
		userProfile.setStreet(userProfileDTO.getStreet());
		userProfile.setHomeNumber(userProfileDTO.getHomeNumber());
		userProfile.setPostCode(userProfileDTO.getPostCode());
		userProfileRepository.save(userProfile);
	}

}
