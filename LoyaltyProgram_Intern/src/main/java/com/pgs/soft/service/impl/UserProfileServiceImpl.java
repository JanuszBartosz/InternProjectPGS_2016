package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.UserProfile;
import com.pgs.soft.dto.UserProfileDTO;
import com.pgs.soft.repository.UserProfileRepository;
import com.pgs.soft.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	UserProfileRepository userProfileRepository;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public void fill(UserProfileDTO userProfileDTO) {
		UserProfile userProfile = new UserProfile();
		userProfile.setName(userProfileDTO.getName());
		userProfile.setSurname(userProfileDTO.getSurname());
		userProfileRepository.save(userProfile);
	}

}
