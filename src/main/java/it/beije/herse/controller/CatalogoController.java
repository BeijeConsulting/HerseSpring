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

import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.JoinService;
import it.beije.herse.service.ProductService;
import it.beije.herse.service.UserService;


@Controller
public class CatalogoController {

	@Autowired
	private ProductService productService;
	



	@RequestMapping(path="/shop/catalogo", method = RequestMethod.GET)
	public String catalogoGET(Model model){
		System.out.println("sono in catalogoGet");
		return "./shop/catalogo";
	}

	@RequestMapping (path="/shop/carrellos", method = RequestMethod.POST)
	public String carrellosPost(Model model, @RequestParam String idP) {
		System.out.println("sono in carrellosPost");

		if(model.getAttribute("prodotti")==null) {
			System.out.println("nuovo attributo di model");
			List<Product> prodotti = productService.findAll();
			model.addAttribute("prodotti", prodotti);
		}
		else {
			System.out.println("l'attributo c'è già");
		}
		
		
		return "./shop/catalogo";
	}
	
	


}
