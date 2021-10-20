package it.beije.herse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.Ecommerce.Shop;
import it.beije.herse.entity.Product;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.ProductService;

@Controller
public class CarrelloController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductService productService;
	
	@GetMapping(path = "carrello/riepilogo")
	public String catalogo() {
		System.out.println("sono in catalogo get");
		return "carrello/riepilogo"; 
	}
	
	@RequestMapping(path = "carrello/crea", method = RequestMethod.POST)
	public String addToCart(HttpSession session, Model model, @RequestParam String productId, @RequestParam String quantity) {
		System.out.println("sono in carrello/crea");
		
		Integer userId = (Integer)  session.getAttribute("userId");
		
		System.out.println("userId " + userId);
		
		int prodId = Integer.parseInt(productId);
		int quant = Integer.parseInt(quantity);
		
		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
		
		Shop shop = new Shop();
		
		Product product = productService.findById(prodId);
		System.out.println("product: " + product);
		
		if(product == null) {
			session.setAttribute("prodottoInesistente", "il prodotto selezionato non esiste");
			return"redirect:/catalogo";
		} else {
			int quantityP = product.getQuantity();
			if(quantityP >= quant ) {
				map = shop.addCart(map, prodId, quant);
				quantityP -= quant;
				productService.removeQuantity(prodId, quantityP);
			}
		}
		
		session.setAttribute("map", map);
		System.out.println("map: " + map);
		
		List<Product> products = productRepository.findAll();
		
		session.setAttribute("products", products);
		
//		return "redirect:/catalogo";
		return"carrello/riepilogo";
	}
	
}
