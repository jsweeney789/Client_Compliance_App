package com.skillstorm.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.dto.ClientRecordDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.ClientRecord;
import com.skillstorm.services.ClientRecordService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clientrecord")
public class ClientRecordController {
	
private final ClientRecordService clientrecord;
	
	public ClientRecordController(ClientRecordService clientrecord)
	{
		this.clientrecord =  clientrecord;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClientRecord> getClientRecord(@PathVariable String id)
	{
		try {
			return ResponseEntity.ok(this.clientrecord.getClientRecord(id));
		} catch (IdNotFoundException exception) {
			
			return ResponseEntity.notFound().header("message", exception.getMessage()).build(); 
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<ClientRecord>> clientrecords()
	{
		return ResponseEntity.ok(this.clientrecord.getAllClientRecords());
	}
	
	
	
	@PostMapping()
	public ResponseEntity<ClientRecord> addClientRecord(@Valid @RequestBody ClientRecordDto clientrecorddto)
	{
		
			return ResponseEntity.ok(this.clientrecord.addClientRecord(clientrecorddto));
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ClientRecord> updateClientRecord(@PathVariable String id, @RequestBody ClientRecordDto clientrecorddto)
	{
		try {
			return ResponseEntity.ok(this.clientrecord.updateClientRecord(id, clientrecorddto));
		} catch (IdNotFoundException exception) {
			
			return ResponseEntity.notFound().header("message", exception.getMessage()).build(); 
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ClientRecord> deleteClientRecord(@PathVariable String id)
	{
		try {
			return ResponseEntity.ok(this.clientrecord.deleteClientRecord(id));
		} catch (IdNotFoundException exception) {
			
			return ResponseEntity.notFound().header("message", exception.getMessage()).build(); 
		}
	}
	
	

}
