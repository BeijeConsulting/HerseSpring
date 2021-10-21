package it.beije.herse.controller;

import java.util.List;


import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Carrello;
import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;

import it.beije.herse.service.ProductService;
import it.beije.herse.service.UserService;


@Controller
public class CarrelloController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;


	@RequestMapping(path = "/carrello/add_product", method = RequestMethod.POST)


	public String add(HttpSession session, Model model, @RequestParam(required = false) Integer to_add , @RequestParam int how_many) {
		
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		Product p = productService.findById(to_add);
		carrello.addProduct(p, how_many);
		
		
		System.out.println(carrello.toString());
		
		model.addAttribute("carrello",carrello);
		model.addAttribute("products",productService.productList());
		
		return "benvenuti";

		
		
	}

}