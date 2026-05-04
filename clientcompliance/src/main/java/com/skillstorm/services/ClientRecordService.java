package com.skillstorm.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.skillstorm.dto.ClientRecordDto;
import com.skillstorm.dto.ClientRecordOnboardDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.ClientRecord;
import com.skillstorm.models.OnboardingCase;
import com.skillstorm.repositories.ClientRecordRepository;
import com.skillstorm.repositories.OnboardingCaseRepository;
import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;

@Service
public class ClientRecordService {
	
	private final ClientRecordRepository clientrepo;
	private final OnboardingCaseRepository onboardrepo;
	
	public ClientRecordService(ClientRecordRepository clientrepo,OnboardingCaseRepository onboardrepo)
	{
		this.clientrepo = clientrepo;
		this.onboardrepo = onboardrepo;
		
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
		public ClientRecord addClientRecord(ClientRecordDto newrecord) 
		{
		
			return this.clientrepo.save(ClientRecordDto.convertToClientRecord(newrecord));
				
			
		}
	
	
	//Update
	public ClientRecord updateClientRecord(String id, ClientRecordDto newrecord) throws IdNotFoundException
	{
		ClientRecord record = clientrepo.findById(id).orElseThrow(()->new IdNotFoundException("Client Record Not Found"));
		if(record.isDeleted())
			throw new IdNotFoundException("This record is deleted: ",record);
		ClientRecord holdrecord = ClientRecordDto.convertToClientRecord(newrecord);
		holdrecord.setId(record.getId());
		return this.clientrepo.save(holdrecord);
			
		
	}
	
	//Delete Record
	public ClientRecord deleteClientRecord(String id) throws IdNotFoundException
	{
		ClientRecord record = clientrepo.findById(id).orElseThrow(()->new IdNotFoundException("Client Record Not Found"));
		if(record.isDeleted())
			throw new IdNotFoundException("This record is deleted: ",record);
		record.setDeleted(true);
		return this.clientrepo.save(record);	
	}
	
	public List<ClientRecordOnboardDto> getAllClientRecordOnboard() 
	{
		List<ClientRecord> clientrecords = clientrepo.findAll().stream().filter(record-> !record.isDeleted()).collect(Collectors.toList());
		return clientrepo.findAll().stream().filter(record-> !record.isDeleted()).map((client)-> 
		new ClientRecordOnboardDto(client.getId(),client.getFirstName(), client.getLastName(),
				client.getType(),client.getSector(),client.getDomicile(),client.getPhoneNumber(),
				client.getEmail(), onboardrepo.findByClientId(client.getId()).orElse(null))).collect(Collectors.toList());
		
		//(String id, String firstName, String lastName, 
//		ClientType type, IndustrySector sector, CountryDomicile domicile,
//		String phoneNumber, String email, OnboardingCase boardcase)
		
	}
	
	

}
