package com.dto;


import com.model.Address;
import com.model.Employee;

public class EmployeeDTO {
	
	private Employee employees;
	
	private Address address;

	public Employee getEmployee() {
		return employees;
	}

	public void setEmployee(Employee employees) {
		this.employees = employees;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
