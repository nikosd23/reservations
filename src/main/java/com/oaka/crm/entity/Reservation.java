package com.oaka.crm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;
//Select r.trainingId from Reservation r where r.customerId = :customerId
@Entity
@Table(name="CUSTOMER_TRAINING")
@NamedQueries({
	@NamedQuery(name = "reservation.findAll",
				query = "select r from Reservation r"),
	@NamedQuery(name = "reservation.findByTrainingId",
				query = "select r from Reservation r where r.trainingId = :trainingId"),
	@NamedQuery(name = "reservation.findByCustomerId",
				query = "select r from Reservation r where r.customerId = :customerId"),
})
public class Reservation implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUSTOMER_ID", insertable=false, updatable=false)
	private Long customerId;
	
	@Id
	@Column(name="TRAINING_ID", insertable=false, updatable=false)
	private Long trainingId;
	
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="TRAINING_ID")
	private Training training;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date datePerfomed;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Date getDatePerfomed() {
		return datePerfomed;
	}

	public void setDatePerfomed(Date datePerfomed) {
		this.datePerfomed = datePerfomed;
	}
	
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
