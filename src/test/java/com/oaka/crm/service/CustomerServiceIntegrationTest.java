package com.oaka.crm.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oaka.crm.entity.Customer;
import com.oaka.crm.entity.Reservation;
import com.oaka.crm.entity.Training;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
public class CustomerServiceIntegrationTest {
	
	@Test
    public void testMarkerMethod() {
    }

	@Autowired
    private CustomerService customerService;
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private ReservationService reservationService;
	
	@BeforeClass
	public static void init(){
	}

	@Test
	@DirtiesContext
    public void testCountPeople() {
        
		Customer p = new Customer();
		p.setFirstName("tesst");
		p.setLastName("test");
		p.setHomeNum("342424");
		p.setBirthDate(new Date());
		p.setEmail("nikosd23@gmail.com");
		p.setMobileNum("234234");
		
		customerService.create(p);
		Assert.assertEquals(1, customerService.count());
        
    }
	
	@Test
	@DirtiesContext
	public void testAddCustomerAndTrainings() {
        
		Customer p = new Customer();
		p.setFirstName("nikos");
		p.setLastName("chrys");
		p.setHomeNum("342424");
		p.setBirthDate(new Date());
		p.setEmail("boo@gmail.com");
		p.setMobileNum("234234");
		customerService.create(p);
		
		Customer c = new Customer();
		c.setFirstName("geo");
		c.setLastName("pet");
		c.setHomeNum("342424");
		c.setBirthDate(new Date());
		c.setEmail("booz@gmail.com");
		c.setMobileNum("234234");
		customerService.create(c);
		
		Training t = new Training();
		t.setInstructor("chris");
		t.setType("spinning");
		t.setCapacity(20L);
		t.setTimeScheduled(new Date());
		t.setTimeToAllowBooking(new Date());
		trainingService.create(t);
		
		Assert.assertEquals(2, customerService.count());
		Assert.assertEquals(1, trainingService.count());
        
    }
	
	@Test
	@DirtiesContext
	public void testReservationAddition() throws Exception{
		Customer c = new Customer();
		c.setFirstName("geo");
		c.setLastName("pet");
		c.setHomeNum("342424");
		c.setBirthDate(new Date());
		c.setEmail("booz@gmail.com");
		c.setMobileNum("234234");
		customerService.create(c);
		
		Training t = new Training();
		t.setInstructor("chris");
		t.setType("spinning");
		t.setCapacity(20L);
		t.setTimeScheduled(new Date());
		t.setTimeToAllowBooking(new Date());
		trainingService.create(t);
		
		Reservation reservation = new Reservation();
		reservation.setCustomer(c);
		reservation.setTraining(t);
		reservation.setDatePerfomed(new Date());
		reservationService.create(reservation);
		
		Assert.assertEquals(reservation, reservationService.getReservationsByCustomerId(c.getId()));
		
	}

}
