package com.oaka.crm;

import java.util.List;

public interface CustomerManager {
	
	/**
	 * Get all customers in the system
	 * @return all customers
	 */
	public List<Customer> getAllCustomers();

	/**
	 * Find an customer by its number.
	 * @param id the customer id
	 * @return the customer
	 */
	public Customer getCustomer(Integer id);
	
	/**
	 * Find an customer by its username
	 * @param username the customer username
	 * @return the customer
	 */
	public Customer getCustomerByUserName(String username);

	/**
	 * Takes a changed customer and persists any changes made to it.
	 * @param account The customer with changes
	 */
	public void update(Customer customer);


}
