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

import com.fuze.takehome.model.Designation;
import com.fuze.takehome.mybatis.DesignationMapper;

public class DesignationService {

	private static final Logger log = LoggerFactory.getLogger(DesignationService.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
	//Keeps track of the first time a department name was created
	private static final Map<String, Date> existingDesignationNames= new HashMap<String, Date>();
	
	@Inject
	protected DesignationMapper mapper;

	@Transactional
	public Designation create(Designation department) {
		mapper.create(department);
		
		//Designation Name is not a unique field
		//However, print out a warning message to the log whenever 
		//we see a new department with a previously encountered name.
		//Not the most real-world scenario but serves the purposes. 
		if(department.getName() != null) {
			Date existingNameDate = existingDesignationNames.get(department.getName());
			if(null != existingNameDate) {
				log.warn("Created a new Designation with the previously used name '" 
						+ department.getName() + "'. Name first seen on " + dateFormat.format(existingNameDate));
			}
			else {
				existingDesignationNames.put(department.getName(), new Date());
			}
		}
		
		return department;
	}
	
	@Transactional
	public Designation read(Long id) {
		Designation department = mapper.read(id);
		if (department != null) {
			return department;
		} else {
			throw new NotFoundException();
		}
	}

	@Transactional
	public Designation delete(Long id) {
		throw new NotSupportedException();
	}
}
