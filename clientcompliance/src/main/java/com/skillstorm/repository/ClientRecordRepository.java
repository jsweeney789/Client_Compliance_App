package com.skillstorm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.model.ClientRecord;

public interface ClientRecordRepository extends MongoRepository<ClientRecord, String> {

}
