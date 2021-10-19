package it.beije.herse.Ecommerce;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@GetMapping(path = "/loginShop")
	public String loginShop() {
		System.out.println("sono in loginShop");
		return "loginShop"; 
	}
	
	@RequestMapping(path = "/loginShop", method = RequestMethod.POST)
	public String loginShopPost() {
		System.out.println("sono in loginShop");
		return "loginShop"; 
	}

	@RequestMapping(path = "/catalogo", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, @RequestParam String email, @RequestParam String password) {
		System.out.println("Credenziali: " + email + " " + password);
		
		int userId = new Shop().checkCredential(email, password);
		
		model.addAttribute("userId", userId);
		
		if(userId == 0) {
			model.addAttribute("credenziali", "Credenziali Sbagliate, <br> Inserisci le credenziali giuste");
			return"loginShop";
		} else {
			List<Product> products = new Shop().findProducts();
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			Integer cont = 1;
			model.addAttribute("products", products);
			model.addAttribute("map", map);
//			session.setAttribute("products", products);
//			session.setAttribute("map", map);
//			session.setAttribute("cont", cont);
//			session.setAttribute("userID", userId);
			return "catalogo";
		}

		
	}
	
}
