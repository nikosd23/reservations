package com.oaka.crm.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oaka.crm.entity.Customer;
import com.oaka.crm.service.CustomerService;

@Controller
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping("/customerSummary")
	public void getCustomerSummary(Model model) {
		model.addAttribute("customers", customerService.load());
	}

	@RequestMapping("/customerDetails")
	public void getcustomerDetails(Long entityId, Model model) {
		try {
			model.addAttribute("customer", customerService.getCustomerById(entityId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setRequiredFields(new String[] {"number", "name"});
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/editCustomer")
	public void getEditCustomer(Long entityId, Model model) {
		try {
			model.addAttribute("customer", customerService.getCustomerById(entityId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(method=RequestMethod.POST, value="/editCustomer")
	public String postEditCustomer(Customer customer, BindingResult bindingResult) {
		validateCustomer(customer, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editCustomer";
		}
		customerService.merge(customer);
		return "redirect:/customers/customerDetails?entityId=" + customer.getId();
	}
	
	/* Validations */
	public void validateCustomer(Customer customer, Errors errors) {
    	if (!StringUtils.hasText(customer.getFirstName())) {
    		errors.rejectValue("firstName", "empty.value");
    	}
       	if (!StringUtils.hasText(customer.getLastName())) {
    		errors.rejectValue("lastName", "empty.value");
    	}
    }  

}
