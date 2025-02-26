package com.bcrypt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bcrypt.entity.ContactUs;
import com.bcrypt.repository.ContactUsRepository;
import com.bcrypt.service.ContactUsService;

@Service
public class ContactUsServiceImpl implements ContactUsService{
	
	
	private ContactUsRepository contactUsRepository;

	public ContactUsServiceImpl(ContactUsRepository contactUsRepository) {
		super();
		this.contactUsRepository = contactUsRepository;
	}

	@Override
	public ContactUs addContactUs(ContactUs contactUs) {
		return this.contactUsRepository.save(contactUs);
	}

	@Override
	public List<ContactUs> getAllContactUs() {
		return this.contactUsRepository.findAll();
	}

}
