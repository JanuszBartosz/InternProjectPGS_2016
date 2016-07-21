package com.pgs.soft.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Role;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ChangePasswordRequestDTO;
import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.repository.UserRepository;
import com.pgs.soft.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserDTO, Integer> implements UserService, UserDetailsService{
	

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	protected CrudRepository<User, Integer> getCrudRepository() {
		return (CrudRepository<User, Integer>) userRepository;
	}

	@Override
	protected User mapDtoToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		user.setRole(Role.USER);
		return user;
	}

	@Override
	protected UserDTO mapEntityToDto(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
	
	
	public Optional<User> getUserByEmail(String email){
		return userRepository.findOneByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		User user = getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));		
		return user;
	}
	
	public void changePassword(ChangePasswordRequestDTO passwordDTO) {
			User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			loggedUser.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));
			userRepository.save(loggedUser);												
	}

}
