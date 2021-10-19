package it.beije.herse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;




import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;

@Controller
public class ShopController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping(path="/shop/login",  method = RequestMethod.GET)
	public String accessoGet() {
		System.out.println("Sono in accessoGet");
		return "shop/accesso_ecommerce";
	}
	
	@RequestMapping(path="/shop/login",  method = RequestMethod.POST)
	public String accesso(Model model, @Validated User user) {
		System.out.println("Sono in accessoPost");
		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println("email: " + email);
		List<User> utente = userRepository.findByEmailAndPassword(email, password);
		System.out.println(utente);
		if(utente.size()==1) {
			model.addAttribute("user", utente.get(0));
			return "./shop/catalogo";
		}
		
		else {
			model.addAttribute("error", "Credenziali errate");
			return "./shop/accesso_ecommerce";
		}
		
		
	}	
	
	@RequestMapping(path="/shop/iscriviti",  method = RequestMethod.GET)
	public String iscrivitiGet() {
		System.out.println("sono in iscrivitiGet");
		return "./shop/iscriviti";
	}
	
	
	@RequestMapping(path="/shop/iscriviti",  method = RequestMethod.POST)
	public String iscrivitiPost(Model model, @Validated User user) {
		
		userRepository.save(user);
		return "./shop/accesso_ecommerce";
	}
	

}
