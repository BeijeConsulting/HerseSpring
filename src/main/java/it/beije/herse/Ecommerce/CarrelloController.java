package it.beije.herse.Ecommerce;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Product;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.repository.UserRepository;

@Controller
public class CarrelloController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(path = "carrello/crea", method = RequestMethod.POST)
	public String addToCart(HttpSession session, Model model, @RequestParam String productId, @RequestParam String quantity) {
		System.out.println("sono in carrello/crea");
		
		Integer userId = (Integer)  session.getAttribute("userId");
		
		System.out.println("userId " + userId);
		
		int prodId = Integer.parseInt(productId);
		int quant = Integer.parseInt(quantity);
		
		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
		
		Shop shop = new Shop();
		
		boolean prodExists = productRepository.existsById(prodId);
		System.out.println("product Exists: " + prodExists);
		
		List<Product> product = productRepository.findById(prodId);
		System.out.println("product: " + product);
		
		int quantityP = 0;
		
		for(Product p: product) {
			quantityP = p.getQuantity();
		}
		
		if(prodExists) {
			if(quantityP >= quant ) {
				map = shop.addCart(map, prodId, quant);
			}
		}
		session.setAttribute("map", map);
		System.out.println("map: " + map);
		
		List<Product> products = productRepository.findAll();
		
		session.setAttribute("prodRiepilogo", products);
		
		return "redirect:/catalogo";
	}
	
}
