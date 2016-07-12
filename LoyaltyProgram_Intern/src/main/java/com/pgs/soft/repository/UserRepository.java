package com.pgs.soft.repository;

import java.util.Optional;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
=======
import org.springframework.data.repository.CrudRepository;
>>>>>>> feature/Authentication_functionality
import org.springframework.stereotype.Repository;

import com.pgs.soft.domain.User;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findOneByEmail(String email);
=======
public interface UserRepository extends CrudRepository<User, Integer>{
>>>>>>> feature/Authentication_functionality

	 Optional<User> findOneByEmail(String email);
}
