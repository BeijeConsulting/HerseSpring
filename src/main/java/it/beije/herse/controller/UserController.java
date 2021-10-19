package it.beije.herse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.User;


import javax.servlet.http.HttpSession;

import it.beije.herse.repository.UserRepository;



@Controller
public class UserController {
	

	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login"; // /WEB-INF/views/ + login + .jsp
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String auth(Model model, @RequestParam String email, @RequestParam String password) {
		
		List<User> users = userRepository.findAll();
		
		for(User u : users)
			if(u.getEmail().equals(email) && u.getPassword().equals(password))
				model.addAttribute("user",u);
		
		return "home";
		
	}	

	@RequestMapping(path = "/user/insert", method = RequestMethod.GET)
	public String getInsertPage() {
		return "user/insert_user";
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user) {
		
		System.out.println("insert user : " + user);
		
		userRepository.save(user);
		
		System.out.println("after save : " + user);
		
		model.addAttribute("message", "User " + user.getEmail() + " added");
		
		return "home"; // /WEB-INF/views/ + user/insert_user + .jsp
	}

	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
	public String getListUsers(Model model, @RequestParam(required = false) String name) {

		List<User> users;
		if (name != null && name.length() > 0) {
			users = userRepository.findByName(name);
		} else {
			users = userRepository.findAll();			
		}
		model.addAttribute("users", users);
		
		return "user/list";
	}
	
}
