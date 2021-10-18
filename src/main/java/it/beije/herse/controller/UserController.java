package it.beije.herse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


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
}
