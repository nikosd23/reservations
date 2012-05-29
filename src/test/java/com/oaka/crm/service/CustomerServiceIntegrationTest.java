package com.oaka.crm.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oaka.crm.entity.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
@Configurable
@Ignore
public class CustomerServiceIntegrationTest {
	
	@Test
    public void testMarkerMethod() {
    }

	@Autowired
    private CustomerService customerService;
	
	@BeforeClass
	public static void init(){
	}

	@Test
	@DirtiesContext
	@Ignore
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

}
