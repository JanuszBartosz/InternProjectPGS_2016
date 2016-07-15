package com.pgs.soft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pgs.soft.domain.UserProfile;

@Repository
public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

}
