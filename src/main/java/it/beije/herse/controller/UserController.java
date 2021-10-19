package it.beije.herse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.User;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String login() {
		return "login"; // /WEB-INF/views/ + login + .jsp
	}
	
	@RequestMapping(path = "/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "login";
		
	}
	
	@RequestMapping(path = "/auth", method = RequestMethod.POST)
	public String auth(HttpSession session, @RequestParam String email, @RequestParam String password) {
		
		String redirect = "";
		
		return redirect;
		
	}
	
}
