package com.pgs.soft.service.impl;

import java.util.Optional;
import java.util.UUID;

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
import com.pgs.soft.service.EmailService;
import com.pgs.soft.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, UserDTO, Integer>
		implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	EmailService emailService;

	@Override
	protected CrudRepository<User, Integer> getCrudRepository() {
		return userRepository;
	}

	@Override
	protected User mapDtoToEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
		user.setRole(Role.USER);
		user.setRegistrationToken(userDTO.getRegistrationToken());
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

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = getUserByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
		return user;
	}

	@Override
	public void changePassword(ChangePasswordRequestDTO passwordDTO) {
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		loggedUser.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));
		userRepository.save(loggedUser);
	}

	@Override
	public Boolean checkUUID(String registrationToken) {

		Optional<User> user = userRepository.findOneByRegistrationToken(registrationToken);

		if (user.isPresent()) {
			user.get().setIsActive(true);
			user.get().setRegistrationToken(null);
			userRepository.save(user.get());
			return true;
		}
		return false;
	}

	@Override
	public void register(UserDTO userDTO) {

		String registrationToken = String.valueOf(UUID.randomUUID());
		userDTO.setRegistrationToken(registrationToken);
		saveOrUpdate(userDTO);
		emailService.sendConfirmationEmail(userDTO.getEmail(), userDTO.getEmail(), registrationToken);
	}
}
