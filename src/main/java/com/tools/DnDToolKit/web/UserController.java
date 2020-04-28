package com.tools.DnDToolKit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
	
	@GetMapping("/")
	 public String showHome() {
		 
		 
		 return "welcome";
	 }
	
	@GetMapping("/login")
	 public String showLogin() {
		 System.out.println("HELLO");
		 
		 return "login";
	 }
	@PostMapping("/login")
	 public String showLoginPost() {
		 System.out.println("H12ELLO");
		 
		 return "login";
	 }
	
	
	@GetMapping("/signup")
	 public String showSignup() {
		 
		 
		 return "signup";
	 }
	
	@GetMapping("/features")
	 public String showFeatures() {
		 
		 
		 return "features";
	 }

}
