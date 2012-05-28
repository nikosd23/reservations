package com.oaka.crm.internal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.oaka.crm.Customer;
import com.oaka.crm.CustomerManager;

public class HibernateCustomerManager implements CustomerManager{
	
	private SessionFactory sessionFactory;

	/**
	 * Creates a new Hibernate account manager.
	 * @param sessionFactory the Hibernate session factory
	 */
	public HibernateCustomerManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		return getCurrentSession().createQuery("from Customer").list();
	}

	@Transactional(readOnly = true)
	public Customer getCustomer(Integer id) {
		return (Customer) getCurrentSession().load(Customer.class, id);
	}

	@Transactional
	public void update(Customer customer) {
		getCurrentSession().update(customer);
	}
	
	@Transactional
	public Customer getCustomerByUserName(String userName){
		Query query = getCurrentSession().getNamedQuery("customer.findCustomerByUserName").setString("userName", userName);
		return (Customer)query.uniqueResult();
	}

	/**
	 * Returns the session associated with the ongoing reward transaction.
	 * @return the transactional session
	 */
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


}
