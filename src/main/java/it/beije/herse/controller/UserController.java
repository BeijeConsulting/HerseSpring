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


	public String auth(Model model, @RequestParam(required = false) String user, @RequestParam String pass) {
		System.out.println("sono in login post");

		/* if (username.equals("Pippo")) {
			model.addAttribute("username", username);
			return "benvenuti";
		} else {
			model.addAttribute("error", "Credenziali errate");
			return "login";
		} */

		if (user.equals("sam") && pass.equals("uele")) {
			model.addAttribute("username", user);

			return "benvenuti";
		} else {
			model.addAttribute("error", "credenziali errate!!");
			return "home";
		}
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.GET)
	public String getInsertPage() {
		return "user/insert_user";
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user
//			@RequestParam String email, @RequestParam String password,
//			@RequestParam String surname, @RequestParam String name
			) {
		
		
	 	User u = userRepository.findByEmail(user.getEmail());
		
	 	if(u!=null) {
	 		
	 		model.addAttribute("already", "already exists");
	 		
	 		return "user/insert_user";
	 	} else {
				
	    System.out.println("insert user : " + user);
		
		userRepository.save(user);
		
		System.out.println("after save : " + user);
		
		model.addAttribute("message", "User " + user.getEmail() + " added");
		
		return "user/insert_user"; // /WEB-INF/views/ + user/insert_user + .jsp
	 	}
	}

	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
	public String getListUsers(Model model, @RequestParam(required = false) String name) {
		
//		User user1 = new User();
//		user1.setFirstName("Pippo");
//		user1.setLastName("Rossi");
//		user1.setUsername("PippoRossi");
//
//		User user2 = new User();
//		user2.setFirstName("Mauro");
//		user2.setLastName("Bianchi");
//		user2.setUsername("MauroBianchi");
//		
//		User user3 = new User();
//		user3.setFirstName("Vincenzo");
//		user3.setLastName("Verdi");
//		user3.setUsername("VV");
//		
//		List<User> users = new ArrayList<User>();
//		users.add(user1);
//		users.add(user2);
//		users.add(user3);

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
