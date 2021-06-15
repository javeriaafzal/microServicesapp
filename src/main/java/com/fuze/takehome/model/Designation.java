package com.fuze.takehome.model;

import javax.validation.constraints.NotNull;

public class Designation {

	private Long id;

	@NotNull(message = "userId cannot be null")
	private Long userId;

	@NotNull(message = "name cannot be null")
	private String name;

	private String description;

	@NotNull(message = "active cannot be null")
	private boolean active;
	
	@NotNull(message = "departmentId cannot be null")
	private Long departmentId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Designation withId(Long id) {
		this.id = id;
		return this;
	}

	public Designation withUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Designation withName(String name) {
		this.name = name;
		return this;
	}

	public Designation withDescription(String description) {
		this.description = description;
		return this;
	}

	public Designation withActive(boolean active) {
		this.active = active;
		return this;
	}
	public Designation withDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
		return this;
	}
}
