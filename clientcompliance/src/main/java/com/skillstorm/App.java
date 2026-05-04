package com.skillstorm;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skillstorm.models.CaseNote;
import com.skillstorm.models.ClientRecord;
import com.skillstorm.models.KycCheck;
import com.skillstorm.models.OnboardingCase;
import com.skillstorm.models.User;
import com.skillstorm.repositories.ClientRecordRepository;
import com.skillstorm.repositories.OnboardingCaseRepository;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.types.ClientType;
import com.skillstorm.types.CountryDomicile;
import com.skillstorm.types.IndustrySector;
import com.skillstorm.types.KycCheckResult;
import com.skillstorm.types.KycCheckType;
import com.skillstorm.types.OnboardingCaseStage;
import com.skillstorm.types.Role;

@SpringBootApplication
public class App implements CommandLineRunner {
	
	@Autowired
	ClientRecordRepository clientrepo;
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	OnboardingCaseRepository onboardrepo;
	
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
		
		KycCheck k = new KycCheck();
		k.setDate(Instant.now());
		k.setNotes("Notes for testing");
		k.setResult(KycCheckResult.PENDING_REVIEW);
		k.setType(KycCheckType.PEP_SCREENING);
		
		KycCheck k2 = new KycCheck();
		k2.setDate(Instant.now());
		k2.setNotes("Notes for testing again");
		k2.setResult(KycCheckResult.FAIL);
		k2.setType(KycCheckType.ID_VERIFICATION);
		
		CaseNote note = new CaseNote();
		note.setAuthorName("Jim");
		note.setText("Testing Notes");
		note.setTimeStamp(Instant.now());
		
		CaseNote note2 = new CaseNote();
		note2.setAuthorName("Bob");
		note2.setText("Testing Notes again");
		note2.setTimeStamp(Instant.now());
		
		List<KycCheck> kycchecks = List.of(k,k2);
		List<CaseNote> notes = List.of(note,note2);
		
		OnboardingCase case1 = new OnboardingCase();
		case1.setChecks(kycchecks);
		case1.setClientId(client.getId());
		case1.setNotes(notes);
		case1.setStage(OnboardingCaseStage.KYC_IN_PROGRESS);
		
		onboardrepo.save(case1);
		
		
		
		
		
	}
}