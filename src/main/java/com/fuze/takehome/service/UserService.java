package com.fuze.takehome.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;

import org.springframework.transaction.annotation.Transactional;


import com.fuze.takehome.model.User;
import com.fuze.takehome.mybatis.UserMapper;


public class UserService {


	@Inject
	public UserMapper mapper;

	@Transactional
	public User create(User user) {
		mapper.create(user);
		return user;		
	}

	@Transactional
	public User read(Long id) {
		User user = mapper.read(id);
		if (user != null) {
			return user;
		} else {
			throw new NotFoundException();
		}
	}
	//it should be userIds.get(i) instead of userIds.get(0)
	@Transactional
	public List<User> list() {
		LinkedList<User> userReturnList  = new LinkedList<User>();
		ArrayList<Long> userIds = new ArrayList<Long>(mapper.list());
		for(int i = 0; i < userIds.size(); i++) {
			userReturnList.add(mapper.read(userIds.get(i)));
		}
		return userReturnList;
	}
	//dont throw an exception if user not found, exception is unhandled, return null if user is not found or an exception if not deleted
	@Transactional
	public User delete(Long id) {
		User user = this.read(id);
		if (user  == null) {
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
			return user;
		}
		else throw new NotSupportedException();
	}	
}
