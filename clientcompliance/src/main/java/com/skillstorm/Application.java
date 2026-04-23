package com.skillstorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skillstorm.model.ClientRecord;
import com.skillstorm.model.User;
import com.skillstorm.repository.ClientRecordRepository;
import com.skillstorm.repository.UserRepository;
import com.skillstorm.type.ClientType;
import com.skillstorm.type.CountryDomicile;
import com.skillstorm.type.IndustrySector;
import com.skillstorm.type.Role;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	ClientRecordRepository clientrepo;
	@Autowired
	UserRepository userrepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		ClientRecord client = new ClientRecord("Bob","Builder", ClientType.CORPORATE, IndustrySector.OTHER, CountryDomicile.UNITED_STATES, "111-111-1111","testing@gmail.com");
		
		User user = new User("John","Tester","something@gmail.com", "333-333-3333","password",Role.Relationship_Manager);

		userrepo.save(user);
		clientrepo.save(client);

		
	}

}
