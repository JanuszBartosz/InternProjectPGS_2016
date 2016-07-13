package com.pgs.soft.service;

import com.pgs.soft.dto.UserProfileDTO;

public interface UserProfileService {

	public void fill(UserProfileDTO userProfileDTO, String userEmail);
}
