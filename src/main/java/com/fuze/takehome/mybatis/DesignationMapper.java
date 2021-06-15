package com.fuze.takehome.mybatis;

import javax.inject.Named;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.fuze.takehome.model.Designation;


@Named
public interface DesignationMapper {
	
	@Insert("INSERT into takeHome.designations "
			+ "(user_id, name, description, active,department_id) "
			+ "VALUES "
			+ "(#{in.userId}, #{in.name}, #{in.description}, #{in.active}),#{in.department_id})")
	@Options(useGeneratedKeys=true, keyProperty="in.id")
	public int create(@Param("in") Designation in);

	@Select("SELECT "
			+ "id, user_id, name, description, active,department_id) "
			+ "FROM takeHome.designations "
			+ "WHERE id = #{id} ")
	@Results(value = { 
			@Result(property = "id", 			column = "id"),
			@Result(property = "userId", 	column = "user_id"), 
			@Result(property = "name", 			column = "name"),
			@Result(property = "description", 	column = "description"),
			@Result(property = "active",		column = "active"),
			@Result(property = "departmentId",		column = "department_id"),
	})
	public Designation read(Long id);
	
	@Delete("DELETE FROM takeHome.designations WHERE id = #{id}")
	public int delete(Long id);
}

