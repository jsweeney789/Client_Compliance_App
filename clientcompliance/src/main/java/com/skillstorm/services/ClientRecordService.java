package com.skillstorm.services;

import java.util.List;

import com.skillstorm.models.ClientRecord;
import com.skillstorm.repositories.ClientRecordRepository;

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
