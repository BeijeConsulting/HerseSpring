package it.beije.herse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EcommerceController {

	@GetMapping(path= "/catalogo")
	public String catalogo()  {
		System.out.println("sono in catalogo get");
		return "catalogo";
	}
}
