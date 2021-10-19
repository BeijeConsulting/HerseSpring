package it.beije.herse.my.controller;

import java.util.List;

import javax.persistence.Access;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
		return "myview/mylogin";
	}

	@RequestMapping(path = "/redirectLogIn", method = RequestMethod.POST)
	public String redirectLogIn() {
		return "myview/mylogin";
	}

	@RequestMapping(path = "/acess", method = RequestMethod.POST)
	public String acess(Model model, @RequestParam(required = true) String email, @RequestParam String password) {
		System.out.println("sono in login POST");

		UserModel userModel = new UserModel();
		List<Users> users = userModel.getUsers();
		for (Users u : users) {
			if (u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password)) {
				model.addAttribute("authError", "");
				model.addAttribute("name", u.getName());
				model.addAttribute("surname", u.getSurname());
				model.addAttribute("authUser", u);

				return "myview/myhome";
			}
		}

		String authError = "Ricontrolla username e password";
		model.addAttribute("authError", authError);
		return "myview/mylogin";

	}

	@RequestMapping(path = "/registration", method = RequestMethod.POST)
	public String registration() {
		return "myview/myregistration";
	}

	@RequestMapping(path = "/backhome", method = RequestMethod.POST)
	public String backhome() {
		return "myview/myhome";
	}
	
	@RequestMapping(path = "/logOut", method = RequestMethod.POST)
	public String logOut(Model model) {
		model.addAttribute("authUser", null);
		return "myview/myhome";
	}

	@RequestMapping(path = "/registrationConfirmation", method = RequestMethod.POST)
	public String registrationConfirmation(Model model, @Validated Users user,
			@RequestParam(required = true) String passwordCheck) {

		if (user != null) {// controllare che i campi non siano vuoti) {

			model.addAttribute("tmpUser", user);

			UserModel userModel = new UserModel();
			List<Users> users = userModel.getUsers();
			for (Users u : users) {
				System.out.println("email exist? " + u.getEmail().equalsIgnoreCase(user.getEmail()));
				System.out.println("psw correct? " + passwordCheck.equals(user.getPassword()));
				if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
					// user gia registrato
					String error = "L'utente e gia registrato";
					model.addAttribute("error", error);
					model.addAttribute("name", "");
					model.addAttribute("surname", "");
					model.addAttribute("email", "");
				} else if (!passwordCheck.equals(user.getPassword()) && passwordCheck.equals("")) {
					// non registrato ma psw errata
					String error = "Le password inserite non coincidono";
					model.addAttribute("error", error);
				} else {
					// va tutto ok quindi aggiungo user al db
					model.addAttribute("error", "");
					model.addAttribute("name", user.getName());
					model.addAttribute("surname", user.getSurname());
					model.addAttribute("authUser", u);
					model.addAttribute("tmpUser", null);
					return "myview/myhome";
				}
			}
			return "myview/myregistration";
		} else {
			return "myview/myregistration";
		}
	}
}
