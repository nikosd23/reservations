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
import com.oaka.crm.dao.TrainingDAO;
import com.oaka.crm.entity.Customer;
import com.oaka.crm.entity.Training;

@Service
@RemoteProxy(name="dwrTrainingService")
@Transactional
public class TrainingService {
	
	private static final Log logger = LogFactory.getLog(TrainingService.class);
	
	@Autowired
	private TrainingDAO trainingDAO;
	
	public List<Training> load(){
		try {
			return trainingDAO.listAll();
		} catch (Exception e) {
			logger.error(e,e);
			return new ArrayList<Training>();
		}
	}
	
	public Training getTrainingById(Long id) throws Exception{
		try {
			return trainingDAO.find(id);
		} catch (Exception e) {
			logger.error(e,e);
			throw e;
		}
	}
	
	public Training getTrainingByType(String type) throws Exception{
		//TODO Add implementation
		return null;
	}
	
	public void delete(Training entity) throws Exception{
		try {
			trainingDAO.delete(entity);
		} catch (Exception e) {
			logger.error(e, e);
			throw e;
		}
	}
	
	public void create(Training entity){
		try{
			trainingDAO.create(entity);
		} catch (Exception e) {
			logger.error(e,e);
		}
		
	}
	
	public void merge(Training entity){
		try {
			trainingDAO.merge(entity);
		} catch (Exception e) {
			logger.error(e,e);
		}
	}
	
	public int count(){
		int result = 0;
		try {
			result = trainingDAO.listAll().size();
		} catch (Exception e) {
			logger.error(e,e);
		}
		return result;
	}
	
	public List<Training> getAvailableTrainingsToBook(){
		//TODO add implementation
		return null;
	}

}
