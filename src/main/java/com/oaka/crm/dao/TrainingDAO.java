package com.oaka.crm.dao;

import org.springframework.stereotype.Component;

import com.oaka.crm.entity.Training;

@Component
public class TrainingDAO extends GenericJpaDAO<Training, Long>{
	
	public TrainingDAO(){
		super(Training.class);
	}

}
