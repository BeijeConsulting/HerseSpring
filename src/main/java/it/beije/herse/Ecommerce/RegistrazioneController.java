package it.beije.herse.Ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrazioneController {
	
	@GetMapping(path = "/user/registrazione")
	public String getPageRegistrazione() {
		return "user/registrazione";
	}
	
	@RequestMapping(path = "/user/registrazione", method = RequestMethod.POST)
	public String ControlloRegistrazione(Model model, @Validated User user) {
		
		int usId = new Shop().checkCredential(user.getEmail(), user.getPassword());
		
		if(!user.getEmail().contains("@")) {
			model.addAttribute("emailSbagliata", "Email Sbagliata <br> inseriscine una corretta");
			return"user/registrazione";
		} 
		if(usId != 0) {
			model.addAttribute("utenteRegistrato", "Utente già registrato");
			return "user/registrazione";
		} else {
			System.out.println("utente registrato: " + user);
			Shop shop = new Shop();
			shop.insertUser(user);
			model.addAttribute("faiLogin", "Effettua il login, dopo la registrazione");
			return"redirect:/loginShop";
		}
	
	}
	
	
}
