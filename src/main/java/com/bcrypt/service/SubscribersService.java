package com.bcrypt.service;

import java.util.List;

import com.bcrypt.entity.Subscribers;

public interface SubscribersService {
	
	//add
	Subscribers addSubscriber(Subscribers subscribers);
	
	//delete by id
	void deleteSubscriberById(Long id);
	
	//get all
	List<Subscribers> getAllSubscribers();
	
	//get by id
	Subscribers getById(Long id);
	
	//get by email
	Subscribers getByEmail(String email);

}