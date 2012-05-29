package com.oaka.crm.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.oaka.crm.entity.Customer;
import com.oaka.crm.entity.Reservation;
import com.oaka.crm.entity.Training;

@Component
public class CustomerDAO extends GenericJpaDAO<Customer, Long>{
	
	public CustomerDAO(){
		super(Customer.class);
	}
	
	public List<Training> getTrainings(Long customerId){
		List<Training> trainings = new ArrayList<Training>();
		Customer customer = find(customerId);
		Collection<Reservation> reservations = customer.getReservations();
		for(Reservation reservation : reservations){
			trainings.add(reservation.getTraining());
		}
		return trainings;
		
	}

}
