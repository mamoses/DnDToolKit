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
	        	    Sort.Order.desc("initiative"));
		 model.addAttribute("players", playerRepository.findAll(sort));
		 return "initiative";
	 }
	 @PostMapping("/initiative")
	 public String showInitiativePost(@Valid Player player, BindingResult result, Model model){
		 
		 Sort sort = Sort.by(
	        	    Sort.Order.desc("initiative"));
		 model.addAttribute("players", playerRepository.findAll(sort));
		 return "initiative";
	 }
	
	 
	
	 
	 
	 @PostMapping("/addplayer")
	 public String addPlayer(@Valid Player player, BindingResult result, Model model) {
		 if (result.hasErrors()) {
			
			 return "add-player";
		 }
		 	playerRepository.save(player);
	        model.addAttribute("players", playerRepository.findAll());
		 return "redirect:/initiative";
	 }
	 
	 @GetMapping("/addplayer")
	 public String addPlayerGet( Player player, BindingResult result, Model model) {
		 if (result.hasErrors()) {
	            return "add-player";
	        }
		 
	     model.addAttribute("players", playerRepository.findAll());
		 return "add-player";
	 }
	 
	 @GetMapping("/delete/{id}")
	    public String deletePlayer(@PathVariable("id") long id, Model model) {
	        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        playerRepository.delete(player);
	        model.addAttribute("players", playerRepository.findAll());
	        return "redirect:/initiative";
	    }
	 
	 @GetMapping("/edit/{id}")
	    public String editPlayer(@PathVariable("id") long id, Model model) {
	        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        model.addAttribute("player", player);
	        return "edit-player";
	    }
	 
	 @PostMapping("/update/{id}")
	    public String updatePlayer(@PathVariable("id") long id, @Valid Player player, Model model, BindingResult result) {
		 if (result.hasErrors()) {
	            player.setId(id);
	            return "edit-player";
	        }
	        playerRepository.save(player);
	        model.addAttribute("users", playerRepository.findAll());
	        return "redirect:/initiative";
	    }

}
