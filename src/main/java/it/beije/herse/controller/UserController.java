package it.beije.herse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login"; // /WEB-INF/views/ + login + .jsp
	}

	@RequestMapping(path = "/auth", method = RequestMethod.POST)
	public String auth(Model model, @RequestParam(required = false) String username, @RequestParam String password, HttpServletRequest request) {
		System.out.println("sono in auth(...)");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		model.addAttribute("username", username);
		HttpSession session = request.getSession();
		session.setAttribute("username",username);
		
		return "catalogo";
	}


	@RequestMapping(path="/carrellos", method=RequestMethod.POST)
	public String carrellos(Model model, HttpServletRequest request,  HttpSession session) {
//		String username = (String) model.getAttribute("username");
		String username = (String)session.getAttribute("username");
		model.addAttribute("username", username);
		System.out.println("username "+ username);
		return "carrello";

	}
}