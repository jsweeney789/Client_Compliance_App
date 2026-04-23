package com.skillstorm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.skillstorm.models.ClientRecord;

public interface ClientRecordRepository extends MongoRepository<ClientRecord, String> {

}
