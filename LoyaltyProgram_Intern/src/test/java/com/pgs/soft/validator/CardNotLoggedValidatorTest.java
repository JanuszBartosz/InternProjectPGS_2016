package com.pgs.soft.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.AddCardNotLoggedRequestDTO;
import com.pgs.soft.service.impl.CardServiceImpl;
import com.pgs.soft.service.impl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CardNotLoggedValidatorTest {

	@Mock
	private UserServiceImpl userServiceMock;

	@Mock
	private CardServiceImpl cardServiceMock;

	@Mock
	private Errors errors;

	@InjectMocks
	private CardNotLoggedValidator cardNotLoggedValidator = new CardNotLoggedValidator();

	private AddCardNotLoggedRequestDTO cardDTO;

	private Optional<User> optionalUser;

	private User user;

	@Before
	public void setUp() throws Exception {
		user = new User();
		user.setId(1);
		cardDTO = new AddCardNotLoggedRequestDTO();
		cardDTO.setEmail("email@adres.com");
		cardDTO.setName("Name");
		cardDTO.setSurname("Surname");
	}

	@Test
	public void testSupportsSuccess() {
		boolean supports = cardNotLoggedValidator.supports(AddCardNotLoggedRequestDTO.class);
		assertTrue(supports);
	}

	@Test
	public void testSupportsFailure() {
		boolean supports = cardNotLoggedValidator.supports(Object.class);
		assertFalse(supports);
	}

	@Test
	public void testValidateSuccess() {
		// Given
		optionalUser = Optional.ofNullable(new User());
		Mockito.when(userServiceMock.getUserByEmailAndNameAndSurname(cardDTO.getEmail(), cardDTO.getName(),
				cardDTO.getSurname())).thenReturn(optionalUser);
		Mockito.when(userServiceMock.getUserByEmail(cardDTO.getEmail())).thenReturn(optionalUser);
		Mockito.when(cardServiceMock.hasActiveCard(user.getId())).thenReturn(false);

		// When
		cardNotLoggedValidator.validate(cardDTO, errors);

		// Then
		verify(errors, never()).reject(anyString());
	}

	@Test
	public void testValidateFailureUserNotExists() {
		// Given
		optionalUser = Optional.ofNullable(null);
		Mockito.when(userServiceMock.getUserByEmailAndNameAndSurname(cardDTO.getEmail(), cardDTO.getName(),
				cardDTO.getSurname())).thenReturn(optionalUser);
		Mockito.when(userServiceMock.getUserByEmail(cardDTO.getEmail())).thenReturn(optionalUser);
		Mockito.when(cardServiceMock.hasActiveCard(user.getId())).thenReturn(false);

		// When
		cardNotLoggedValidator.validate(cardDTO, errors);

		// Then
		verify(errors).reject("card.user_not_exist");
	}

	@Test
	public void testValidateFailureHasActiveCard() {
		// Given
		optionalUser = Optional.ofNullable(user);
		Mockito.when(userServiceMock.getUserByEmailAndNameAndSurname(cardDTO.getEmail(), cardDTO.getName(),
				cardDTO.getSurname())).thenReturn(optionalUser);
		Mockito.when(userServiceMock.getUserByEmail(cardDTO.getEmail())).thenReturn(optionalUser);
		Mockito.when(cardServiceMock.hasActiveCard(user.getId())).thenReturn(true);

		// When
		cardNotLoggedValidator.validate(cardDTO, errors);

		// Then
		verify(errors).reject("card.has_active_card");
	}

}
