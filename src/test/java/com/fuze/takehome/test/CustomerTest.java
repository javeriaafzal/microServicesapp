package com.fuze.takehome.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import com.fuze.takehome.model.Customer;
import com.fuze.takehome.model.User;
import com.fuze.takehome.model.Customer.Contact;
import com.fuze.takehome.service.CustomerService;

public class CustomerTest extends AbstractEntityTest {

	@Inject
	private CustomerService service;
	
	/**
	 * Test the Customer Entity and Customer Service.
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
	public void testCustomer() {
		//test list
		List<Customer> allCustomers = service.list();
		Assert.assertNotNull(allCustomers);
		Assert.assertEquals(1, allCustomers.size());
		
		//test create
		service.create(new Customer()
				.withActive(true)
				.withContact(new Contact()
            						.withEmail("tom@ibm.com")
            						.withFirstName("Tom")
            						.withLastName("Smith"))
				.withName("IBM")
				);
		
		List<Customer> allCustomersUpdated = service.list();
		Assert.assertEquals(2, allCustomersUpdated.size());
		
		//test read
		int presentSize=2;
		long index=(long)presentSize-1;
		Customer second=service.read(index);
		Assert.assertEquals("IBM", second.getName());
		Assert.assertEquals(Long.valueOf(1), second.getId());
		
	
		
		
		//test update
		Customer newCustomer = new Customer()
				.withActive(true)
				.withId((long) 1)
				.withName("Apple");
		List<Customer> updatedCustomers=service.update(newCustomer);
		//check
		Customer secondagain=service.read(index);
		Assert.assertEquals("Apple", secondagain.getName());
		Assert.assertEquals(2, updatedCustomers.size());
	}
}
