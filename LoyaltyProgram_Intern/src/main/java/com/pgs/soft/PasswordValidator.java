package com.pgs.soft;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.pgs.soft.dto.PasswordDTO;

@Component
public class PasswordValidator implements Validator {

/*	@Autowired
	private HttpSession session;
	
	@Autowired 
	private UserServiceImpl userService;*/
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.equals(PasswordDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordDTO passwordDTO = (PasswordDTO) target;
		validatePassword(errors, passwordDTO);
		
	}

	private void validatePassword(Errors errors, PasswordDTO passwordDTO) {
		
		if( !(passwordDTO.getOldPassword() != null && !passwordDTO.getOldPassword().isEmpty()) )
			errors.reject("old.password.empty", "Old password is empty!");
				
		if( !(passwordDTO.getNewPassword() != null && !passwordDTO.getNewPassword().isEmpty()) )
			errors.reject("new.password.empty", "New password is empty!");
		
		if( !(passwordDTO.getNewPasswordRepeat() != null && !passwordDTO.getNewPasswordRepeat().isEmpty()) )
			errors.reject("repeat.password.empty", "Repeat password is empty!");
				
		if( !(passwordDTO.getNewPasswordRepeat() != null && passwordDTO.getNewPassword().equals(passwordDTO.getNewPasswordRepeat())) )
			errors.reject("passwords.not.equal", "Password and repeated password do not match!");
		
		
		
		/*String email = (String) session.getAttribute("email");
		String oldPasswordHash = "";
		
		if(email==null)
			errors.reject("not.logged", "You are not logged!");
		else
		{	
			Optional<com.pgs.soft.domain.User> user = userService.getUserByEmail(email);
			oldPasswordHash = user.get().getPassword();
		}
		if(! new BCryptPasswordEncoder().matches(passwordDTO.getOldPassword(), oldPasswordHash))
			errors.reject("old.password.wrong", "Wrong old password!");
		
		if(new BCryptPasswordEncoder().matches(passwordDTO.getNewPassword(), oldPasswordHash))
			errors.reject("passwords.same", "New password the same as old one!");*/
		
	}
		

}
