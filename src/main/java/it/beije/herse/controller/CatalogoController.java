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
import it.beije.herse.shop.Carrello;


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
	public String carrellosPost(Model model, @RequestParam String idP, @RequestParam String quantita, HttpSession session) {
		System.out.println("sono in carrellosPost");
		List<Product> prodotti = (List<Product>) session.getAttribute("prodotti");
		model.addAttribute("prodotti", prodotti);
		Carrello carrello = null;

		if(session.getAttribute("carrello")!=null) {
			System.out.println("if");
			carrello = (Carrello)session.getAttribute("carrello");
		}
		else {
			System.out.println("else");
			carrello = new Carrello();
			session.setAttribute("carrello",carrello);
		}
		Integer id = Integer.valueOf(idP);
		Integer quantity = Integer.valueOf(quantita);
		carrello.addProduct(id, quantity);


		return "./shop/catalogo";
	}




}
