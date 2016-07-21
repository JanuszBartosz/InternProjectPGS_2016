package com.pgs.soft.service;

import org.springframework.stereotype.Service;

import com.pgs.soft.dto.UserProfileDTO;

@Service
public interface UserProfileService {

	public void save(UserProfileDTO userProfileDTO);
	
	public UserProfileDTO getUserProfile();
}
