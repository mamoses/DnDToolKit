package com.tools.DnDToolKit.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
		 
		 Sort sort = Sort.by(
	        	    Sort.Order.asc("name"));
		 
		 model.addAttribute("players", playerRepository.findAll(sort));
		 
		 return "initiative";
	 }
	 
	
	 
	 
	 @PostMapping("/addplayer")
	 public String addPlayer(@Valid Player player, BindingResult result, Model model) {
		 if (result.hasErrors()) {
	            return "add-player";
	        }
	        
	        playerRepository.save(player);
	        System.out.println(player.getName());
	        Sort sort = Sort.by(
	        	    Sort.Order.asc("initiative"));
	        model.addAttribute("players", playerRepository.findAll(sort));
		 
		 return "redirect:/initiative";
	 }
	 
	 @GetMapping("/addplayer")
	 public String addPlayerGet(@Valid Player player, BindingResult result, Model model) {
		 
		 
		 return "add-player";
	 }

}
