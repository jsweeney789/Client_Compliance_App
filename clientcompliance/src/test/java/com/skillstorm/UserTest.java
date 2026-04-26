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

import com.skillstorm.dto.UserDto;
import com.skillstorm.exceptions.IdNotFoundException;
import com.skillstorm.models.User;
import com.skillstorm.repositories.UserRepository;
import com.skillstorm.services.UserService;
import com.skillstorm.types.Role;

class UserTest {
	
	User user = new User("69ed40c483ed8c22d027b8df","John","Tester","something@gmail.com", "333-333-3333","password",Role.RELATIONSHIP_MANAGER);
	User user2 = new User("69eae79453a8074c5fc90491","Kim","Possible","email@yahoo.com", "444-444-4444","password2",Role.COMPLIANCE_OFFICER);
	Optional<User> fakeuser = Optional.of(user);
	UserDto userdto = new UserDto("Will","Tested","something@juno.com", "000-000-0000","password123",Role.ADMINISTRATOR);
	List<User> users = new ArrayList<>(List.of(user,user2));
	
	//Optional<List<User>> fakeusers = Optional.of(users);
	
	UserRepository userrepo;
	UserService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		userrepo = mock(UserRepository.class);
		service = new UserService(userrepo);
	}

	@Test
	void GetUserById() throws IdNotFoundException {
		Mockito.when(userrepo.findById("69ed40c483ed8c22d027b8df")).thenReturn(fakeuser);
		
		assertEquals(user, service.getUser("69ed40c483ed8c22d027b8df"));
	}
	
	@Test
	void GetAllUsers() {
		Mockito.when(userrepo.findAll()).thenReturn(users);
		assertEquals(users, service.getAllUsers());
		
	}
	
	@Test
	void AddUser() {
		service.addUser(userdto);
		//Showing userrepo save was called at least once which would save the user
		Mockito.verify(userrepo, times(1)).save(Mockito.any(User.class));
	}
	
	@Test
	void UpdateUserById() throws IdNotFoundException {
		Mockito.when(userrepo.findById("69ed40c483ed8c22d027b8df")).thenReturn(fakeuser);
		Mockito.when(userrepo.save(Mockito.any(User.class))).thenAnswer(method -> method.getArgument(0));
		
		
		User updateduser = service.updateUser("69ed40c483ed8c22d027b8df", userdto);
		assertEquals(updateduser.getFirstName(), userdto.firstName());
		assertEquals(updateduser.getLastName(), userdto.lastName());
		assertEquals(updateduser.getEmail(), userdto.email());
		assertEquals(updateduser.getPhoneNumber(), userdto.phoneNumber());
		assertEquals(updateduser.getRole(), userdto.role());
	
	}
	
	@Test
	void DeleteUserById() throws IdNotFoundException {
		Mockito.when(userrepo.findById("69ed40c483ed8c22d027b8df")).thenReturn(fakeuser);
		Mockito.when(userrepo.save(Mockito.any(User.class))).thenAnswer(method -> method.getArgument(0));
		
		assertEquals(service.deleteUser("69ed40c483ed8c22d027b8df").isDeleted(), true);
	}

}
