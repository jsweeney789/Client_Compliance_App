package com.skillstorm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skillstorm.models.ClientRecord;
import com.skillstorm.models.User;
import com.skillstorm.repositories.ClientRecordRepository;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;
import com.skillstorm.types.Role;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	@Autowired
	ClientRecordRepository clientrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	 PasswordEncoder encoder;
	 @Autowired
	 private MongoTemplate mongoTemplate;
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {
    	
    	mongoTemplate.getDb().drop();
		
		ClientRecord client = new ClientRecord("Bob","Builder", ClientType.CORPORATE, IndustrySector.OTHER, CountryDomicile.UNITED_STATES, "111-111-1111","testing@gmail.com");
		ClientRecord client2 = new ClientRecord("Tim","Cooker", ClientType.INSTITUTIONAL, IndustrySector.DEFENSE_ARMS, CountryDomicile.ARGENTINA, "222-222-2222","newemail@gmail.com");
		User user = new User("John","Tester","something@gmail.com", "333-333-3333",this.encoder.encode("password"),Role.RELATIONSHIP_MANAGER);
		User user2 = new User("Kim","Possible","email@yahoo.com", "444-444-4444",this.encoder.encode("password2"),Role.COMPLIANCE_OFFICER);
		User user3 = new User("Test","Test","test@gmail.com", "444-444-4444",this.encoder.encode("test"),Role.ADMINISTRATOR);
		userrepo.save(user);
		userrepo.save(user2);
		userrepo.save(user3);
		clientrepo.save(client);
		clientrepo.save(client2);
		
	}
}