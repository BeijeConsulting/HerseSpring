package it.beije.herse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EcommerceController {

	@GetMapping(path= "/shop/catalogo")
	public String catalogo()  {
		System.out.println("sono in catalogo get");
		return "/shop/catalogo";
	}
	
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
