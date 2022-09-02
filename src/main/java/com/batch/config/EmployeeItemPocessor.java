package com.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.batch.model.Employee;

public class EmployeeItemPocessor implements ItemProcessor<String,String>{

/*	@Override
	public Employee process(Employee employee) throws Exception {
		employee.setRegion(employee.getRegion().toUpperCase());
		employee.setCountry(employee.getCountry().toUpperCase());
		employee.setItemType(employee.getItemType().toUpperCase());
		employee.setSalesChannel(employee.getSalesChannel().toUpperCase());
		return employee;
	}*/

	@Override
	public String process(String item) throws Exception {
		return null;
	}

}
