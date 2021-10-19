package it.beije.herse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.User;


@Controller
public class UserController {
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login"; // /WEB-INF/views/ + login + .jsp
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String auth(Model model, @RequestParam(required = false) String username, @RequestParam String password) {
		System.out.println("sono in auth(...)");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		if (username.equals("Pippo")) {
			model.addAttribute("username", username);
			return "benvenuti";
		} else {
			model.addAttribute("error", "Credenziali errate");
			return "login";
		}
		
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.GET)
	public String getInsertPage() {
		return "user/insert_user";
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user
//			@RequestParam String username, @RequestParam String password,
//			@RequestParam String firstName, @RequestParam String lastName
			) {
		System.out.println("insert user : " + user);
		
		return "user/insert_user"; // /WEB-INF/views/ + user/insert_user + .jsp
	}

	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
	public String getListUsers(Model model) {
		
		User user1 = new User();
		user1.setFirstName("Pippo");
		user1.setLastName("Rossi");
		user1.setUsername("PippoRossi");

		User user2 = new User();
		user2.setFirstName("Mauro");
		user2.setLastName("Bianchi");
		user2.setUsername("MauroBianchi");
		
		User user3 = new User();
		user3.setFirstName("Vincenzo");
		user3.setLastName("Verdi");
		user3.setUsername("VV");
		
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		
		model.addAttribute("users", users);
		
		return "user/list";
	}
	

}
