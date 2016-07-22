package com.pgs.soft.service;

import java.util.Optional;

import com.pgs.soft.domain.User;
import com.pgs.soft.dto.ChangePasswordRequestDTO;
import com.pgs.soft.dto.UserDTO;

public interface UserService extends GenericService<UserDTO, Integer> {

	public Optional<User> getUserByEmail(String email);

	public void changePassword(ChangePasswordRequestDTO passwordDTO);

	public Optional<User> getUserByEmailAndNameAndSurname(String email, String name, String surname);

	public Boolean checkUUID(String uuid);

	public void register(UserDTO userDTO);
}