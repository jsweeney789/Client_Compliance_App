package com.skillstorm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.model.User;

public interface UserRepository extends MongoRepository<User, String> {

}
