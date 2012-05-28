package com.oaka.crm.web;

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

import com.oaka.crm.Customer;
import com.oaka.crm.CustomerManager;

@Controller
public class CustomerController {
	
	private CustomerManager customerManager;
	
	@Autowired
	public CustomerController(CustomerManager customerManager) {
		this.customerManager = customerManager;
	}

	@RequestMapping("/customerSummary")
	public void getCustomerSummary(Model model) {
		model.addAttribute("customers", customerManager.getAllCustomers());
	}

	@RequestMapping("/customerDetails")
	public void getcustomerDetails(Integer entityId, Model model) {
		model.addAttribute("customer", customerManager.getCustomer(entityId));
	}
	
	@RequestMapping("/dashboard")
	public String showdashboard(Principal principal, Model model) {
		String view = "dashboard";
		final String username = principal.getName();
		if(username.equalsIgnoreCase("admin")){
			view = "redirect:admin";
		}else{
			Customer customer = customerManager.getCustomerByUserName(username);
			model.addAttribute("customer", customer);
		}
		return view;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setRequiredFields(new String[] {"number", "name"});
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/editCustomer")
	public void getEditCustomer(Integer entityId, Model model) {
		model.addAttribute("customer", customerManager.getCustomer(entityId));
	}
	
    public void validateCustomer(Customer customer, Errors errors) {
    	if (!StringUtils.hasText(customer.getFirstName())) {
    		errors.rejectValue("firstName", "empty.value");
    	}
       	if (!StringUtils.hasText(customer.getLastName())) {
    		errors.rejectValue("lastName", "empty.value");
    	}
    }  

	@RequestMapping(method=RequestMethod.POST, value="/editCustomer")
	public String postEditCustomer(Customer customer, BindingResult bindingResult) {
		validateCustomer(customer, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editCustomer";
		}
		customerManager.update(customer);
		return "redirect:/customers/customerDetails?entityId=" + customer.getId();
	}

}
