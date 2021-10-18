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
	// public String auth(Model model, @RequestParam(required = false) String username, @RequestParam String password) {
    public String auth(Model model, @RequestParam String user, @RequestParam String pass) {
		
		
		if (user.equals("sam") && pass.equals("uele")) {
			model.addAttribute("username", user);

			return "benvenuti";
		} else {
			model.addAttribute("error", "credenziali errate!!");
			return "home";
		}
		
	}
}
