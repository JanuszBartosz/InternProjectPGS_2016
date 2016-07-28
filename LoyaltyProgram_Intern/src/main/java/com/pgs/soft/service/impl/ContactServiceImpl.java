package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.ContactMessage;
import com.pgs.soft.dto.ContactMessageDTO;
import com.pgs.soft.repository.ContactMessageRepository;
import com.pgs.soft.service.ContactService;
import com.pgs.soft.service.EmailService;

@Service
public class ContactServiceImpl extends GenericServiceImpl<ContactMessage, ContactMessageDTO, Long>
		implements ContactService {

	@Autowired
	EmailService emailService;

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
		contactMessage.setSubject(contactMessageDTO.getSubject());
		contactMessage.setMessage(contactMessageDTO.getMessage());
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

	@Override
	public void saveAndSendEmail(ContactMessageDTO contactMessageDTO) {
		saveOrUpdate(contactMessageDTO);
		emailService.sendContactConfirmationEmail(contactMessageDTO.getEmail(), contactMessageDTO.getSubject(),
				contactMessageDTO.getMessage());
	}

}
