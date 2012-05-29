package com.oaka.crm.entity;

import java.io.Serializable;

public class ReservationId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long customerId;
	
	private Long trainingId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}
		

}
