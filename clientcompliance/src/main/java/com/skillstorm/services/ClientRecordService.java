package com.skillstorm.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillstorm.models.ClientRecord;
import com.skillstorm.repositories.ClientRecordRepository;

@Service
public class ClientRecordService {
	
	private final ClientRecordRepository clientrepo;
	
	public ClientRecordService(ClientRecordRepository clientrepo)
	{
		this.clientrepo = clientrepo;
	}
	
	public ClientRecord getClientRecord(String id) throws Exception
	{
		return clientrepo.findById(id).orElseThrow(()->new Exception("Client Record Not FOund"));
		
	}
	
	public List<ClientRecord> getAllClientRecords() throws Exception
	{
		return clientrepo.findAll();
		
	}
	
	
	

}
