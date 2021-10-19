package it.beije.herse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Product;
import it.beije.herse.repository.ProductRepository;

@Controller
public class EcommerceController {

	@Autowired
	ProductRepository productRepository;
	
//	@GetMapping(path= "/shop/catalogo")
//	public String catalogo()  {
//		System.out.println("sono in catalogo get");
//		return "/shop/catalogo";
//	}
	
//	@GetMapping(path = "/shop/catalogo")
//	public String getListUsers(Model model, @RequestParam(required = false) Integer id) {
//		System.out.println("sono in list products get");
//		List<Product> listProducts;
//		
//		if(listProducts != null && listProducts.size() >0) {
//			listProducts = productRepository.findAll();
//		} else {
//			Optional<Product> p = productRepository.findById(id);
//			listProducts = p;
//		}
//		model.addAttribute("listProducts", listProducts);
//		
//		return "shop/catalogo";
//	}
	
	@GetMapping(path= "/shop/ordine")
	public String ordine()  {
		System.out.println("sono in ordine get");
		return "/shop/ordine";
	}
	
	@GetMapping(path= "/shop/menu")
	public String menu()  {
		System.out.println("sono in menu get");
		return "/shop/menu";
	}

}
