package it.beije.herse.my.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.bean.Users;
import it.beije.herse.model.UserModel;

@Controller
public class MyUserController {

	@GetMapping(path = "/")
	public String log() {
		System.out.println("sono in login GET");
		return "login";
	}

	@RequestMapping(path = "/acess", method = RequestMethod.POST)
	public String acess(Model model, @RequestParam(required = true) String username, @RequestParam String password) {
		System.out.println("sono in login POST");

		UserModel userModel = new UserModel();
		List<Users> users = userModel.getUsers();
		for (Users u : users) {
			if (u.getEmail().equalsIgnoreCase(username) && u.getPassword().equals(password)) {
				model.addAttribute("authError", "");
				model.addAttribute("name", u.getName());
				model.addAttribute("surname", u.getSurname());
				model.addAttribute("authUser", u);
				
				return "myhome";
			}
		}

		String authError = "Ricontrolla username e password";
		model.addAttribute("authError", authError);
		return "login";

	}

}
