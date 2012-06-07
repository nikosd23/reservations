package com.oaka.crm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.oaka.crm.entity.Reservation;
import com.oaka.crm.entity.ReservationId;
import com.oaka.crm.entity.Training;

@Component
public class ReservationDAO extends GenericJpaDAO<Reservation, ReservationId>{
	
	public ReservationDAO(){
		super(Reservation.class);
	}
	
	public List<Reservation> getReservationsByCustomerId(Long customerId){
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("customerId", customerId);
		List<Reservation> reservations = listByNamedQuery("reservation.findByCustomerId", queryParams);
		return reservations;
	}
}
