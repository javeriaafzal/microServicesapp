package com.fuze.takehome.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.fuze.takehome.model.User;
import com.fuze.takehome.service.UserService;

public class UserTest extends AbstractEntityTest {

	@Inject
	private UserService service;
	
	/**
	 * Test the User Entity and User Service.
	 * 
	 * Assert that entity validation is working correctly and
	 * the service methods are doing what they are supposed to be doing.
	 * 
	 * Before this test executes, AbstractEntityTest takes care of initializing
	 * Spring and starting an in-memory DB instance. DummyDataGenerator is
	 * invoked to insert a couple dummy entities into the database. 
	 * 
	 */
	@Test
	public void testUser() {
		//test list
		List<User> allUsers = service.list();
		Assert.assertNotNull(allUsers);
		Assert.assertEquals(2, allUsers.size());
		
		//test create
		User newUser = new User()
		.withActive(true)
		.withCustomerId(0L)
		.withEmail("myTest@test.com")
		.withFirstName("My")
		.withLastName("Test")
		.withMobileNumber("555-2356-254")
		.withTelephoneNumber("555-8512-763")
		.withUserName("rcastorena");
		
		service.create(newUser);
		List<User> allUsersUpdated = service.list();
		Assert.assertEquals(3, allUsersUpdated.size());
		
		//test read
		int presentSize=3;
		long index=(long)presentSize-1;
		User third=service.read(index);
		Assert.assertEquals("Test", third.getLastName());
		
		//test delete
		User deletedUser=service.delete(index);
		Assert.assertEquals("Test", deletedUser.getLastName());
		List<User> allUsersUpdatedDeletion = service.list();
		Assert.assertEquals(2, allUsersUpdatedDeletion.size());
		
	}
}
