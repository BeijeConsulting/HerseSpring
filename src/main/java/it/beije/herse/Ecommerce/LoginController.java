package it.beije.herse.Ecommerce;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping(path = "/loginShop")
	public String loginShop() {
		System.out.println("sono in loginShop get");
		return "loginShop"; 
	}
	
	@RequestMapping(path = "/loginShop", method = RequestMethod.POST)
	public String loginShopPost() {
		System.out.println("sono in loginShop post");
		return "loginShop"; 
	}

	@RequestMapping(path = "/catalogo", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, @RequestParam String email, @RequestParam String password) {
		System.out.println("Credenziali: " + email + " " + password);
		
//		int userId = new Shop().checkCredential(email, password);
		
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		
		System.out.println("users: " +users);
		
		int userId = 0;
		
		for(User u: users) {
			userId = u.getId();
		}
		
		System.out.println("userId: " + userId);
		
		
		if(userId == 0) {
			model.addAttribute("credenziali", "Credenziali Sbagliate, <br> Inserisci le credenziali giuste");
			return"loginShop";
		} else {
			List<Product> products = productRepository.findAll();
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			Integer cont = 1;
			session.setAttribute("userId", userId);
			model.addAttribute("products", products);
			session.setAttribute("map", map);
			session.setAttribute("cont", cont);
			return "catalogo";
		}

		
	}
	
}
