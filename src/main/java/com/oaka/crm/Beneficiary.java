package com.oaka.crm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * A single beneficiary allocated to an customer. Each beneficiary has a name (e.g. Annabelle) and a savings balance
 * tracking how much money has been saved for he or she to date (e.g. $1000).
 */
@Entity
@Table(name = "T_CUSTOMER_BENEFICIARY")
public class Beneficiary {

	@Id
	@Column(name = "ID")
	private Integer entityId;

	@Column(name = "NAME")
	private String name;



	protected Beneficiary() {
	}

	/**
	 * Creates a new account beneficiary.
	 * @param name the name of the beneficiary
	 */
	public Beneficiary(String name) {
		this.name = name;
	}

	/**
	 * Returns the entity identifier used to internally distinguish this entity among other entities of the same type in
	 * the system. Should typically only be called by privileged data access infrastructure code such as an Object
	 * Relational Mapper (ORM) and not by application code.
	 * @return the internal entity identifier
	 */
	public Integer getEntityId() {
		return entityId;
	}

	/**
	 * Sets the internal entity identifier - should only be called by privileged data access code (repositories that
	 * work with an Object Relational Mapper (ORM)). Should never be set by application code explicitly.
	 * @param entityId the internal entity identifier
	 */
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * Returns the beneficiary name.
	 */
	public String getName() {
		return name;
	}

	

	public String toString() {
		return "name = '" + name ;
	}
}