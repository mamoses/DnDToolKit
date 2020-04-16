package com.tools.DnDToolKit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.tools.DnDToolKit.model.PlayerRepository;

@Controller
public class PlayerController {
	
	private final PlayerRepository playerRepository;
	
	 @Autowired
	 public PlayerController(PlayerRepository playerRepository) {
	        this.playerRepository = playerRepository;
	 }
	 
	 @GetMapping("/")
	 public String showHome() {
		 
		 
		 return "home";
	 }

}
