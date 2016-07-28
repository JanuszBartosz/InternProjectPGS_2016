package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.pgs.soft.domain.ContactMessage;
import com.pgs.soft.dto.ContactMessageDTO;
import com.pgs.soft.repository.ContactMessageRepository;

public class ContactServiceImpl extends GenericServiceImpl<ContactMessage, ContactMessageDTO, Long> {

	@Autowired
	ContactMessageRepository contactMessageRepository;

	@Override
	protected CrudRepository<ContactMessage, Long> getCrudRepository() {
		return contactMessageRepository;
	}

	@Override
	protected ContactMessage mapDtoToEntity(ContactMessageDTO contactMessageDTO) {
		ContactMessage contactMessage = new ContactMessage();
		contactMessage.setEmail(contactMessageDTO.getEmail());
		contactMessage.setSubject(contactMessage.getSubject());
		contactMessage.setMessage(contactMessage.getMessage());
		return contactMessage;
	}

	@Override
	protected ContactMessageDTO mapEntityToDto(ContactMessage contactMessage) {
		ContactMessageDTO contactMessageDTO = new ContactMessageDTO();
		contactMessageDTO.setEmail(contactMessage.getEmail());
		contactMessageDTO.setSubject(contactMessage.getSubject());
		contactMessageDTO.setMessage(contactMessage.getMessage());
		return contactMessageDTO;
	}

}
