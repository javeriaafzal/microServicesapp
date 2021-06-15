package com.fuze.takehome.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;

import org.springframework.transaction.annotation.Transactional;
import com.fuze.takehome.model.Customer;
import com.fuze.takehome.mybatis.CustomerMapper;

public class CustomerService {
	
	@Inject
	private CustomerMapper mapper;

	@Transactional
	public Customer create(Customer customer) {
		mapper.create(customer);
		return customer;		
	}

	@Transactional
	public Customer read(Long id) {
		Customer customer = mapper.read(id);
		if (customer != null) {
			return customer;
		} else {
			throw new NotFoundException();
		}
	}
	@Transactional
	public List<Customer> list() {
		LinkedList<Customer> customerReturnList  = new LinkedList<Customer>();
		ArrayList<Long> cIds = new ArrayList<Long>(mapper.list());
		for(int i = 0; i < cIds.size(); i++) {
			customerReturnList.add(mapper.read(cIds.get(i)));
		}
		return customerReturnList;
	}

	@Transactional
	public Customer delete(Long id) {
		Customer c = this.read(id);
		if (c  == null) {
			return null;
		}
		int count = 0;
		try {
			count = mapper.delete(id);
		}
		catch(Exception e){
			throw e;
		}
		if(count == 1)
		{
			return c;
		}
		else throw new NotSupportedException();
	}	
	
	
	@Transactional
	public List<Customer> update(Customer customer) {
		List<Customer> customers=this.list();
		for(Customer c : customers ){
			if(c.getId()== customer.getId())
			{
				long id=customer.getId();
				String name=customer.getName();
				mapper.update(id,name);
				
			}
		}
		
		return customers;		
	}
}
