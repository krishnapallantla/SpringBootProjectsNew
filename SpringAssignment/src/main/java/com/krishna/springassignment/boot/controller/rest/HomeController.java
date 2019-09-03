package com.krishna.springassignment.boot.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@RequestMapping("/login")
	public ModelAndView loginPage() {
		// public String loginPage() {

		
		  ModelAndView mv = new ModelAndView(); mv.setViewName("login.jsp");
		  
		  return mv;
	}

	/*
	 * @RequestMapping("/logout-success") public String logoutPage() {
	 * 
	 * return "logout.jsp"; }
	 */
}
