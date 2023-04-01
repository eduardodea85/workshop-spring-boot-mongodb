package com.dea.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dea.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{	

}
