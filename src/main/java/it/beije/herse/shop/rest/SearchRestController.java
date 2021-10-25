package it.beije.herse.shop.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.service.SearchService;

@RestController
@RequestMapping("search")
public class SearchRestController {

	@Autowired
	private SearchService searchService;
	
	@GetMapping("user/{parameters}")
	public List<ShopUser> getUsers(@PathVariable("parameters") String parameters) {
		
		return searchService.parseParam(parameters);
	}
	
}
