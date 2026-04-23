package com.skillstorm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillstorm.dto.ClientRecordDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.ClientRecord;
import com.skillstorm.repositories.ClientRecordRepository;

@Service
public class ClientRecordService {
	
	private final ClientRecordRepository clientrepo;
	
	public ClientRecordService(ClientRecordRepository clientrepo)
	{
		this.clientrepo = clientrepo;
	}
	
	//Get by Id
	public ClientRecord getClientRecord(String id) throws IdNotFoundException
	{
		return clientrepo.findById(id).orElseThrow(()->new IdNotFoundException("Client Record Not Found"));
		
	}
	
	//Get All
	public List<ClientRecord> getAllClientRecords() 
	{
		return clientrepo.findAll().stream().filter(record-> !record.isDeleted()).collect(Collectors.toList());
		
	}
	
	//Add
		public ClientRecord addRecord(ClientRecordDto newrecord) 
		{
		
			return this.clientrepo.save(ClientRecordDto.convertToClientRecord(newrecord));
				
			
		}
	
	
	//Update
	public ClientRecord updateRecords(String id, ClientRecordDto newrecord) throws IdNotFoundException
	{
		ClientRecord record = clientrepo.findById(id).orElseThrow(()->new IdNotFoundException("Client Record Not Found"));
		if(record.isDeleted())
			throw new IdNotFoundException("This record is deleted: ",record);
		ClientRecord holdrecord = ClientRecordDto.convertToClientRecord(newrecord);
		holdrecord.setId(record.getId());
		return this.clientrepo.save(holdrecord);
			
		
	}
	
	//Delete Record
	public ClientRecord deleteRecords(String id) throws IdNotFoundException
	{
		ClientRecord record = clientrepo.findById(id).orElseThrow(()->new IdNotFoundException("Client Record Not Found"));
		if(record.isDeleted())
			throw new IdNotFoundException("This record is deleted: ",record);
		record.setDeleted(true);
		return this.clientrepo.save(record);	
	}
	
	
	

}
