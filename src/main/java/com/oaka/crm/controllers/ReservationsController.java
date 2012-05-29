package com.oaka.crm.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oaka.crm.entity.Customer;
import com.oaka.crm.service.CustomerService;

@Controller
public class ReservationsController {
	
	private CustomerService customerService;
	
	@Autowired
	public ReservationsController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping("/dashboard")
	public String showdashboard(Principal principal, Model model) {
		String view = "dashboard";
		final String username = principal.getName();
		if(username.equalsIgnoreCase("admin")){
			view = "redirect:admin";
		}else{
			try {
				Customer customer = customerService.getCustomerByUserName(username);
				model.addAttribute("customer", customer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return view;
	}

}
