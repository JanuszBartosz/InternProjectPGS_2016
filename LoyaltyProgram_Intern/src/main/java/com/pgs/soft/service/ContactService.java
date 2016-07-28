package com.pgs.soft.service;

import com.pgs.soft.dto.ContactMessageDTO;

public interface ContactService {

	void saveAndSendEmail(ContactMessageDTO contactMessageDTO);

}