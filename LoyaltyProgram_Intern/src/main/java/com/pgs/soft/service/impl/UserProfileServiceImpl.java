package com.pgs.soft.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	HobbyRepository hobbyRepository;
	
	@Override
	public UserProfileDTO getUserProfile() {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOneByEmail(loggedUser.getEmail()).get();
		UserProfile userProfile = user.getUserProfile();
		UserProfileDTO userProfileDTO = new UserProfileDTO();
		userProfileDTO = mapEntityToDto(userProfile, userProfileDTO);
		return userProfileDTO;
	}

	@Override
	public void save(UserProfileDTO userProfileDTO) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findOneByEmail(loggedUser.getEmail()).get();
		UserProfile userProfile = user.getUserProfile();
		userProfile = mapDtoToEntity(userProfileDTO, userProfile);
		userProfileRepository.save(userProfile);
	}
	
	private UserProfile mapDtoToEntity(UserProfileDTO userProfileDTO, UserProfile userProfile){
		userProfile.setName(userProfileDTO.getName());
		userProfile.setSurname(userProfileDTO.getSurname());
		userProfile.setCity(userProfileDTO.getCity());
		userProfile.setStreet(userProfileDTO.getStreet());
		userProfile.setHomeNumber(userProfileDTO.getHomeNumber());
		userProfile.setPostCode(userProfileDTO.getPostCode());
		userProfile.getHobbies().clear();
		
		if(userProfileDTO.getHobbies()!=null){
			for(String hobby : userProfileDTO.getHobbies()){
				Optional<Hobby> optionalHobby = hobbyRepository.findOneByHobbyName(hobby);
				optionalHobby.ifPresent(h -> userProfile.getHobbies().add(h));
			}
		}
		
		
		return userProfile;
	}
	
	private UserProfileDTO mapEntityToDto(UserProfile userProfile, UserProfileDTO userProfileDTO){
		userProfileDTO.setName(userProfile.getName());
		userProfileDTO.setSurname(userProfile.getSurname());
		userProfileDTO.setCity(userProfile.getCity());
		userProfileDTO.setStreet(userProfile.getStreet());
		userProfileDTO.setHomeNumber(userProfile.getHomeNumber());
		userProfileDTO.setPostCode(userProfile.getPostCode());
		
		for(Hobby hobby : userProfile.getHobbies()){
			userProfileDTO.getHobbies().add(hobby.getHobbyName());
		}
		
		return userProfileDTO;
	}
}
