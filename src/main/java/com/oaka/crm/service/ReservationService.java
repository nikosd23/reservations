package com.oaka.crm.service;

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
import com.oaka.crm.entity.Training;

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
	


}
