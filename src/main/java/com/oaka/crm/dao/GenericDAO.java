package com.oaka.crm.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, PK extends Serializable> {

	/**
	 * Persist the newInstance object into database
	 */
	PK create(T newInstance);

	/**
	 * Search for an object in the database using the indicated id as primary key
	 */
	T read(PK id);

	/**
	 * Retrieve a cached object or an object that was previously persisted to the database using the indicated id as primary key
	 */
	T find(PK id);

	/**
	 * Save changes made to a persistent object.
	 * Session does not contains an already persistent instance with the same identifier
	 */
	void update(T transientObject);

	/**
	 * Save changes made to a persistent object.
	 * Save your modifications at any time with out knowing about the state of a session
	 */
	T merge(T transientObject);

	/**
	 * Remove an object from persistent storage in the database
	 */
	void /*Object*/ delete(T persistentObject);

	/**
	 * Remove an object from persistent storage in the database using the indicated id as primary key
	 */
	void /*Object*/ deleteById(PK id);

	/**
	 * Synchronize all the changes that are made to the persistent entities back to the underlying database
	 */
	void flush();

	/**
	 * Update the transient object with values taken from the database
	 */
	void refresh(T transientObject);

	/**
	 * Completely clear the session
	 */
	void clear();

	/**
	 * Remove this instance from the session cache
	 */
	void evict(T cachedObject);

	/**
	 * Lists all Entities
	 *
	 * @return
	 */
	List<T> listAll();

	/**
	 * Lists count entries starting from first row.
	 *
	 * @param first
	 * @param count
	 * @return
	 */
	List<T> list(int first, int count);
}
