package com.oaka.crm.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaka.crm.dao.CustomerDAO;
import com.oaka.crm.dao.ReservationDAO;
import com.oaka.crm.dao.TrainingDAO;
import com.oaka.crm.entity.Customer;
import com.oaka.crm.entity.Reservation;

@Service
@RemoteProxy(name="dwrReservationService")
@Transactional
public class ReservationService {
	
	private static final Log logger = LogFactory.getLog(TrainingService.class);
	
	@Autowired
	private TrainingDAO trainingDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private ReservationDAO reservationDAO;
	
	public List<Reservation> load(){
		try {
			return reservationDAO.listAll();
		} catch (Exception e) {
			logger.error(e,e);
			return new ArrayList<Reservation>();
		}
	}
	
	public List<Reservation> getReservationsByCustomerId(Long customerId){
		return reservationDAO.getReservationsByCustomerId(customerId);
	}
	
	public void delete(Reservation entity) throws Exception{
		try {
			reservationDAO.delete(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		}
	}
	
	public void create(Reservation entity){
		try{
			reservationDAO.create(entity);
		} catch (Exception e) {
			logger.error(e,e);
		}
		
	}
	
	public void merge(Reservation entity){
		try {
			reservationDAO.merge(entity);
		} catch (Exception e) {
			logger.error(e,e);
		}
	}
	
	

}
