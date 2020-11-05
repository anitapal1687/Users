package com.example.Users.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

import com.example.Users.constraints.CustomDateValidator;
import com.example.Users.constraints.Unique;
import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="Users", uniqueConstraints = {@UniqueConstraint(columnNames = {"loginId"})})
public class Users {

	
	@Id
	@NotBlank
	@NotNull
    private String id;
	
	@NotNull
	@Column(unique = true,name = "loginId")
	@Unique(message = "Employee login not unique")
	@NotEmpty
	@NotBlank
	private String loginId;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String empName;
	
	@NotNull
	@DecimalMin(value = "0.00", inclusive = false, message="Invalid Salary")
	private Double salary;
	
	@NotNull
	//@CustomDateValidator
	private Date startDate; 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
}
