package com.bcrypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bcrypt.entity.Subscribers;

@Repository
public interface SubscribersRepository extends JpaRepository<Subscribers, Long>{
	
	Subscribers findByEmail(String email);

}
