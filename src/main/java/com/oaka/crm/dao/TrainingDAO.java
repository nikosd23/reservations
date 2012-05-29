package com.oaka.crm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oaka.crm.entity.Customer;
import com.oaka.crm.entity.Training;

@Component
public class TrainingDAO extends GenericJpaDAO<Training, Long>{
	
	public TrainingDAO(){
		super(Training.class);
	}
	
	public List<Customer> getCustomers(Long trainingId){
		List<Customer> customers = new ArrayList<Customer>();
		
		return customers;
	}

}
