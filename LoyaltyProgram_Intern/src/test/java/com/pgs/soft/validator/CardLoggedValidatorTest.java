package com.pgs.soft.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.AddCardRequestDTO;
import com.pgs.soft.service.impl.CardServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CardLoggedValidator.class)
public class CardLoggedValidatorTest {

	@Mock
	CardServiceImpl cardServiceImpl;

	@Mock
	Errors errors;

	@InjectMocks
	CardLoggedValidator cardLoggedValidator = new CardLoggedValidator();

	AddCardRequestDTO cardDTO;

	User loggedUser;

	@Before
	public void setUp() throws Exception {
		loggedUser = new User();
		loggedUser.setId(1);
		cardDTO = new AddCardRequestDTO();
		Authentication auth = new UsernamePasswordAuthenticationToken(loggedUser, null);
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	@Test
	public void testSupportsSuccess() {
		boolean supports = cardLoggedValidator.supports(AddCardRequestDTO.class);
		assertTrue(supports);
	}

	@Test
	public void testSupportsFailure() {
		boolean supports = cardLoggedValidator.supports(Object.class);
		assertFalse(supports);
	}

	@Test
	public void testValidateSuccess() {
		Mockito.when(cardServiceImpl.hasActiveCard(loggedUser.getId())).thenReturn(false);
		cardLoggedValidator.validate(cardDTO, errors);
		verify(errors, never()).rejectValue(anyString(), anyString());
	}

	@Test
	public void testValidateFailure() {
		Mockito.when(cardServiceImpl.hasActiveCard(loggedUser.getId())).thenReturn(true);
		cardLoggedValidator.validate(cardDTO, errors);
		verify(errors).reject("card.has_active_card");
	}

}
