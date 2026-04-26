package com.skillstorm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.skillstorm.dto.ClientRecordDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.ClientRecord;
import com.skillstorm.models.User;
import com.skillstorm.repositories.ClientRecordRepository;
import com.skillstorm.services.ClientRecordService;
import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;

class ClientRecordTest {
	
	ClientRecord client = new ClientRecord("69ea9ce0f1aa9dc7f190327d","Bob","Builder", ClientType.CORPORATE, IndustrySector.OTHER, CountryDomicile.UNITED_STATES, "111-111-1111","testing@gmail.com");
	ClientRecord client2 = new ClientRecord("69eada8ac45de990b8449e1e","Tim","Cooker", ClientType.INSTITUTIONAL, IndustrySector.DEFENSE_ARMS, CountryDomicile.ARGENTINA, "222-222-2222","newemail@gmail.com");
	Optional<ClientRecord> fakerecord = Optional.of(client);
	
	ClientRecordDto clientdto = new ClientRecordDto("Kim","Possiible", ClientType.INSTITUTIONAL, IndustrySector.CRYPTO_ASSETS, CountryDomicile.ARGENTINA, "000-000-0000","updated@gmail.com");
	List<ClientRecord> clients = new ArrayList<>(List.of(client,client2));
	
	ClientRecordRepository clientrepo;
	ClientRecordService service;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		clientrepo = mock(ClientRecordRepository.class);
		service = new ClientRecordService(clientrepo);
	}

	@Test
	void GetClientRecordById() throws IdNotFoundException {
		Mockito.when(clientrepo.findById("69ea9ce0f1aa9dc7f190327d")).thenReturn(fakerecord);
		
		assertEquals(client, service.getClientRecord("69ea9ce0f1aa9dc7f190327d"));
	}
	
	@Test
	void GetAllClientRecords() {
		Mockito.when(clientrepo.findAll()).thenReturn(clients);
		assertEquals(clients, service.getAllClientRecords());
	}
	
	@Test
	void AddClientRecord() {
		service.addClientRecord(clientdto);
		//Showing clientrepo save was called at least once which would save the client
		Mockito.verify(clientrepo, times(1)).save(Mockito.any(ClientRecord.class));
	}
	
	@Test
	void UpdateClientRecordById() throws IdNotFoundException {
		Mockito.when(clientrepo.findById("69ea9ce0f1aa9dc7f190327d")).thenReturn(fakerecord);
		Mockito.when(clientrepo.save(Mockito.any(ClientRecord.class))).thenAnswer(method -> method.getArgument(0));
		
		ClientRecord updatedclient = service.updateClientRecord("69ea9ce0f1aa9dc7f190327d", clientdto);
		assertEquals(updatedclient.getDomicile(), clientdto.domicile());
		assertEquals(updatedclient.getEmail(), clientdto.email());
		assertEquals(updatedclient.getFirstName(), clientdto.firstName());
		assertEquals(updatedclient.getLastName(), clientdto.lastName());
		assertEquals(updatedclient.getPhoneNumber(), clientdto.phoneNumber());
		assertEquals(updatedclient.getSector(), clientdto.sector());
		assertEquals(updatedclient.getType(), clientdto.type());
	}
	
	@Test
	void DeleteClientRecordById() throws IdNotFoundException {
		Mockito.when(clientrepo.findById("69ea9ce0f1aa9dc7f190327d")).thenReturn(fakerecord);
		Mockito.when(clientrepo.save(Mockito.any(ClientRecord.class))).thenAnswer(method -> method.getArgument(0));
		
		assertEquals(service.deleteClientRecord("69ea9ce0f1aa9dc7f190327d").isDeleted(), true);
	}

}
