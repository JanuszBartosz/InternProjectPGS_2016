package com.pgs.soft.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pgs.soft.domain.Role;
import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ChangePasswordRequestDTO;
import com.pgs.soft.dto.UserDTO;
import com.pgs.soft.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl = new UserServiceImpl();
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Test
	public void mapEntityToDtoTest() {
		// Given
		Integer id = new Integer(4);
		String email = "test@test.com";
		String password = "testpassword";

		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);

		// When
		UserDTO userDTO = userServiceImpl.mapEntityToDto(user);

		// Then
		assertEquals(id, userDTO.getId());
		assertEquals(email, userDTO.getEmail());
		assertEquals(password, userDTO.getPassword());
	}

	@Test
	public void mapDtoToEntityTest() {
		// Given
		Integer id = new Integer(4);
		String email = "test@test.com";
		String password = "testpassword";
		String token = "token";

		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setEmail(email);
		userDTO.setPassword(password);
		userDTO.setRegistrationToken(token);
		userServiceImpl.setbCryptPasswordEncoder(bCryptPasswordEncoder);
		// When
		User user = userServiceImpl.mapDtoToEntity(userDTO);

		// Then
		assertEquals(id, user.getId());
		assertEquals(email, user.getEmail());
		assertTrue(bCryptPasswordEncoder.matches(password, user.getPassword()));
		assertEquals(Role.USER, user.getRole());
		assertEquals(token, user.getRegistrationToken());
	}

	@Test
	public void changePasswordTest() {
		// Given
		ChangePasswordRequestDTO passwordDTO = new ChangePasswordRequestDTO();
		String newPassword = "newpassword";
		passwordDTO.setNewPassword(newPassword);
		userServiceImpl.setbCryptPasswordEncoder(bCryptPasswordEncoder);

		Integer id = new Integer(4);
		String email = "test@test.com";
		String password = "testpassword";

		User loggedUser = new User();
		loggedUser.setId(id);
		loggedUser.setEmail(email);
		loggedUser.setPassword(password);

		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
		Optional<User> user = null;
		Mockito.when(userRepository.findOneByEmail(email)).thenReturn(user);

		// When
		userServiceImpl.changePassword(passwordDTO);

		// Then
		assertTrue(bCryptPasswordEncoder.matches(newPassword, loggedUser.getPassword()));
	}

}
