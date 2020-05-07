package com.tools.DnDToolKit.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.tools.DnDToolKit.model.User;
import com.tools.DnDToolKit.model.UserRepository;


@Controller
public class UserController {
	
	private final UserRepository userRepository;
	
	 @Autowired
	 public UserController(UserRepository userRepository) {
	        this.userRepository = userRepository;
	 }
	@GetMapping("/")
	 public String showHome() {
		 
		 return "welcome";
	 }
	
	@GetMapping("/login")
	 public String showLogin(@Valid User user, BindingResult result, HttpServletRequest request) {
		
		 return "login";
	 }
	@GetMapping("/logout")
	 public String showLogout() {
		 
		 
		 return "welcome";
	 }
	@PostMapping("/login")
	 public String showLoginPost(HttpServletRequest request) {
		
		System.out.println("HERE2323!");
		 return "app";
	 }
	
	
	@GetMapping("/signup")
	 public String showSignup(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
		 
		 return "signup";
	 }
	
	@PostMapping("/signup")
	 public String showSignupPost(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
		userRepository.save(user);
		model.addAttribute("user", userRepository.findAll());
		
		 
		 return "welcome";
	 }
	
	@GetMapping("/features")
	 public String showFeatures() {
		 
		 
		 return "features";
	 }

}
