package com.skillstorm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.models.User;

public interface UserRepository extends MongoRepository<User, String> {

}
