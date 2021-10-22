package it.beije.herse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomePage {

	@GetMapping(path = "/")
	public String pippoPlutoPaperino() {
		System.out.println("sono in homepage");
		return "home"; // /WEB-INF/views/ + home + .jsp
	}
	
	@RequestMapping(path = "/benvenuti", method = RequestMethod.GET)
	public String benvenuti() {
		System.out.println("sono in benvenuti");
		return "prodotti";
	}
}
