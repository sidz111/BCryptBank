package com.bcrypt.service;

import java.util.List;

import com.bcrypt.entity.ContactUs;

public interface ContactUsService {
	
	ContactUs addContactUs(ContactUs contactUs);
	
	List<ContactUs> getAllContactUs();

}
