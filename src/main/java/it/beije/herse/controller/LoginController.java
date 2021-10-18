package it.beije.herse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@GetMapping(path = "/loginUser")
	public String loginUser() {
		return "loginUser";
	}
	
	@PostMapping(path = "/authUser")
	public String authUser(Model model, @RequestParam String email, @RequestParam String password) {
		return "menuUser";
	}
}
