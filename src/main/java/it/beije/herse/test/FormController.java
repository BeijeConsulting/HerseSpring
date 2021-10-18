package it.beije.herse.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String index() {
		// index
		return "login";
	}
	
	@RequestMapping(path="/log", method = RequestMethod.POST)
	public String login(Model model, @RequestParam String email, @RequestParam String password) {
		
		System.out.println("EMAIL: "+email);
		model.addAttribute("email", email);
		
		System.out.println("PASSWORD: "+password);
		model.addAttribute("password", password);
		if(email.equalsIgnoreCase("nv@gmail.com") && password.equals("nv")) {
			return "insert";
		}
		
		return "retry";
	}
	
	@RequestMapping(path="/retry", method = RequestMethod.POST)
	public String retry(Model model, @RequestParam String retryAction) {
		
		if(retryAction.equalsIgnoreCase("back")) {
			model.addAttribute("loginMessage", "EMAIL AND PASSWORD NOT FOUND, RETRY");
			return "login";
		}
		
		return "insert";
	}
	
	@RequestMapping(path="/insert", method = RequestMethod.POST)
	public String insert(Model model, @RequestParam String name, @RequestParam String surname) {
		
		if(name.length()>0 && surname.length()>0) {
			model.addAttribute("name", name);
			model.addAttribute("surname", surname);
		}
		
		return "result";
	}
	
	@PostMapping(path = "/index")
	public String backToIndex() {
		return "login";
	}
}
