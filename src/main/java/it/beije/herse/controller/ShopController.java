package it.beije.herse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.beije.herse.entity.Product;
import it.beije.herse.repository.ProductRepository;

@Controller
public class ShopController {
	
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(path = "/showProduct")
	public String showProduct(Model model) {
		
		String redirect = "viewproduct";
		
		List<Product> products = productRepository.findAll();
		
		model.addAttribute("products", products);
		
		return redirect;
		
	}
}
