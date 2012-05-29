package com.oaka.crm.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oaka.crm.dao.TrainingDAO;

@Service
@RemoteProxy(name="dwrTrainingService")
@Transactional
public class TrainingService {
	
	private static final Log logger = LogFactory.getLog(TrainingService.class);
	
	@Autowired
	private TrainingDAO trainingDAO;

}
