package com.fuze.takehome.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.fuze.takehome.model.Department;
import com.fuze.takehome.mybatis.DepartmentMapper;

public class DepartmentService {

	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
	//Keeps track of the first time a department name was created
	private static final Map<String, Date> existingDepartmentNames= new HashMap<String, Date>();
	
	//Thread safety means that any attributes be private which they already are, also the methods (update methods) can be synchronized so that any one thread is altering the table at any one time.Read 
	//method can be accessed by multiple methods however.
	@Inject
	protected DepartmentMapper mapper;

	@Transactional
	public synchronized Department create(Department department) {
		mapper.create(department);
		
		//Department Name is not a unique field
		//However, print out a warning message to the log whenever 
		//we see a new department with a previously encountered name.
		//Not the most real-world scenario but serves the purposes. 
		if(department.getName() != null) {
			Date existingNameDate = existingDepartmentNames.get(department.getName());
			if(null != existingNameDate) {
				log.warn("Created a new Department with the previously used name '" 
						+ department.getName() + "'. Name first seen on " + dateFormat.format(existingNameDate));
			}
			else {
				existingDepartmentNames.put(department.getName(), new Date());
			}
		}
		
		return department;
	}
	
	@Transactional
	public Department read(Long id) {
		Department department = mapper.read(id);
		if (department != null) {
			return department;
		} else {
			throw new NotFoundException();
		}
	}

	@Transactional
	public synchronized Department delete(Long id) {
		throw new NotSupportedException();
	}
}
