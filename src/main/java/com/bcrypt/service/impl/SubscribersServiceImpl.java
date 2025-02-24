package com.bcrypt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcrypt.entity.Subscribers;
import com.bcrypt.repository.SubscribersRepository;
import com.bcrypt.service.SubscribersService;

@Service
public class SubscribersServiceImpl implements SubscribersService{
	
	@Autowired
	private SubscribersRepository subscribersRepository;

	public SubscribersServiceImpl(SubscribersRepository subscribersRepository) {
		super();
		this.subscribersRepository = subscribersRepository;
	}

	@Override
	public Subscribers addSubscriber(Subscribers subscribers) {
		return this.subscribersRepository.save(subscribers);
	}

	@Override
	public void deleteSubscriberById(Long id) {
		this.subscribersRepository.deleteById(id);
	}

	@Override
	public List<Subscribers> getAllSubscribers() {
		return this.subscribersRepository.findAll();
	}

	@Override
	public Subscribers getById(Long id) {
		Optional<Subscribers> s = subscribersRepository.findById(id);
		if(s.isEmpty()) {
			return null;
		}
		else {
			return s.get();
		}
	}

	@Override
	public Subscribers getByEmail(String email) {
		Optional<Subscribers> s = Optional.ofNullable(subscribersRepository.findByEmail(email));
		if(s.isEmpty()) {
			return null;
		}
		else {
			return s.get();
		}
	}

}
