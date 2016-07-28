package com.pgs.soft.repository;

import org.springframework.data.repository.CrudRepository;

import com.pgs.soft.domain.ContactMessage;

public interface ContactMessageRepository extends CrudRepository<ContactMessage, Long> {

}
