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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tools.DnDToolKit.model.Player;
import com.tools.DnDToolKit.model.PlayerRepository;

@Controller
public class PlayerController {
	
	private final PlayerRepository playerRepository;
	
	 @Autowired
	 public PlayerController(PlayerRepository playerRepository) {
	        this.playerRepository = playerRepository;
	 }
	 
	 @GetMapping("/app")
	 public String showHome() {
		 
		 
		 return "home";
	 }
	 @PostMapping("/app")
	 public String showHomePost() {
		 
		 
		 return "home";
	 }
	 
	 
	 @GetMapping("/initiative")
	 public String showInitiative(@Valid Player player, BindingResult result, Model model, HttpSession session){
		 
		 Sort sort = Sort.by(
	        	    Sort.Order.desc("initiative"));
		 
		 List<Player> players = (List<Player>) session.getAttribute("PLAYERS_SESSION");
		
		 try {	// TODO implement this function with better practices
		 Collections.sort(players, new Comparator<Player>() {
			  @Override
			  public int compare(Player player1, Player player2) {
			    return player2.getInitiative().compareTo(player1.getInitiative());
			  }
			});
		 }
		 catch(NullPointerException e){
			 
		 }
		 
		 model.addAttribute("playersSession", players!=null? players:new ArrayList<>());
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
	 public String addPlayer(@Valid Player player, BindingResult result, Model model, HttpServletRequest request) {
		 List<Player> players = (List<Player>) request.getSession().getAttribute("PLAYERS_SESSION");
		 
		 
		 if (result.hasErrors()) {
			
			 return "add-player";
		 }
		 
		 // Make sure the players List doesn't throw any errors for being null
		 if (players == null) {
	            players = new ArrayList<>();
	            request.getSession().setAttribute("PLAYERS_SESSION", players);
	      }
		 
		 players.add(player);
	 	 playerRepository.save(player);
	 	
	 	 
         request.getSession().setAttribute("PLAYERS_SESSION", players!=null? players:new ArrayList<>());
		 return "redirect:/initiative";
	 }
	 
	 @GetMapping("/addplayer")
	 public String addPlayerGet( Player player, BindingResult result, Model model) {
		 if (result.hasErrors()) {
	            return "add-player";
	        }
		
		 return "add-player";
	 }
	 
	 @GetMapping("/delete/{id}")
	    public String deletePlayer(@PathVariable("id") long id, Model model, HttpServletRequest request) {
	        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		 	List<Player> players = (List<Player>) request.getSession().getAttribute("PLAYERS_SESSION");
		 	
		 	int player_position = players.indexOf(player);
		 	
		 	players.remove(player_position);
		 	
		 	playerRepository.delete(player);	
		 	model.addAttribute("playersSession", players!=null? players:new ArrayList<>());
		 	
	        return "redirect:/initiative";
	    }
	 
	 @GetMapping("/edit/{id}")
	    public String editPlayer(@PathVariable("id") long id, Model model, HttpServletRequest request) {
		 	List<Player> players = (List<Player>) request.getSession().getAttribute("PLAYERS_SESSION");
	        Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	        
	        try {
		        int player_position = players.indexOf(player);
		        
		        model.addAttribute("player", players.get(player_position));
		        players.remove(player_position);	// Remove old player info so it does not double add to the Http Session
	        }
	        catch(IndexOutOfBoundsException e) {
	        }
	        return "edit-player";
	    }
	 
	 @PostMapping("/update/{id}")
	    public String updatePlayer(@PathVariable("id") long id, @Valid Player player, BindingResult result, Model model,  HttpServletRequest request) {
		 List<Player> players = (List<Player>) request.getSession().getAttribute("PLAYERS_SESSION");
		 
		 if (result.hasErrors()) {
			
	            player.setId(id);
	            return "edit-player";
	        }
		 	players.add(player);
		
		 	
	        playerRepository.save(player);
	        model.addAttribute("playersSession", players!=null? players:new ArrayList<>());
	        
	        return "redirect:/initiative";
	    }

}
