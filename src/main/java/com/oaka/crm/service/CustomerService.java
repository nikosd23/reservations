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
import com.oaka.crm.entity.Customer;
import com.oaka.crm.entity.Training;

@Service
@RemoteProxy(name="dwrCustomerService")
@Transactional
public class CustomerService {
	
	private static final Log logger = LogFactory.getLog(CustomerService.class);
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public List<Customer> load(){
		try {
			return customerDAO.listAll();
		} catch (Exception e) {
			logger.error(e,e);
			return new ArrayList<Customer>();
		}
	}
	
	public Customer getCustomerById(Long id) throws Exception{
		try {
			return customerDAO.find(id);
		} catch (Exception e) {
			logger.error(e,e);
			throw e;
		}
	}
	
	public Customer getCustomerByUserName(String userName) throws Exception{
		//TODO Add implementation
		return null;
	}
	
	public void delete(Customer entity) throws Exception{
		try {
			customerDAO.delete(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		}
	}
	
	public void create(Customer entity){
		try{
			customerDAO.create(entity);
		} catch (Exception e) {
			logger.error(e,e);
		}
		
	}
	
	public void merge(Customer entity){
		try {
			customerDAO.merge(entity);
		} catch (Exception e) {
			logger.error(e,e);
		}
	}
	
	public int count(){
		int result = 0;
		try {
			result = customerDAO.listAll().size();
		} catch (Exception e) {
			logger.error(e,e);
		}
		return result;
	}
	
	public List<Training> getAvailableTrainingsToBook(){
		return null;
	}

}
