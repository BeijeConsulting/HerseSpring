package it.beije.herse.Ecommerce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;

@Controller
public class RegistrazioneController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(path = "/user/registrazione")
	public String getPageRegistrazione() {
		return "user/registrazione";
	}
	
	@RequestMapping(path = "/user/registrazione", method = RequestMethod.POST)
	public String ControlloRegistrazione(Model model, @Validated User user) {
		
		List<User> users = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		int userId = 0;
		
		for(User u: users) {
			userId = u.getId();
		}
		
		if(!user.getEmail().contains("@")) {
			model.addAttribute("emailSbagliata", "Email Sbagliata <br> inseriscine una corretta");
			return"user/registrazione";
		} 
		if(userId != 0) {
			model.addAttribute("utenteRegistrato", "Utente già registrato");
			return "user/registrazione";
		} else {
			System.out.println("utente registrato: " + user);
//			Shop shop = new Shop();
//			shop.insertUser(user);
			userRepository.save(user);
			model.addAttribute("faiLogin", "Effettua il login, dopo la registrazione");
			return"redirect:/loginShop";
		}
	
	}
	
	
}
