package com.tools.DnDToolKit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.tools.DnDToolKit.model.Player;
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
	 
	 @GetMapping("/initiative")
	 public String showInitiative(@Valid Player player, BindingResult result, Model model){
		 
		 return "initiative";
	 }
	 
	 @GetMapping("/addplayer")
	 public String addPlayer(@Valid Player player, BindingResult result, Model model) {
		 
		 return "initiative";
	 }

}
