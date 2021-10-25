package it.beije.herse.restcontroller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.service.OrderService;
import it.beije.herse.service.UserService;
import it.beije.herse.service.ProductService;


//@Controller
	@RestController
	@RequestMapping("api")
public class SearchRestController {



	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	

    // Nome preciso
	@GetMapping("/search/product/{name}")
	public Product getProduct(@PathVariable("name") String name) {

		return productService.findByName(name);

	}
	
	// Nome indicativo
	@GetMapping("/search/productlike/{name}")
	public List<Product> getProductLike(@PathVariable("name") String name) {

		return productService.findBy_LIKE_Name(name);

	}
	
	// Ricerca utente
		@GetMapping("/search/user/{name}/{surname}")
		public List<User> getUserLike(@PathVariable("name") String name, @PathVariable("surname") String surname) {

			return userService.findByNameAndSurname(name,surname);

		}
		
   // Ricerca utente
				@GetMapping("/search/user/")
				public List<User> getUser(@RequestParam("name") String name, @RequestParam("surname") String surname) {

					return userService.findByNameAndSurname(name,surname);

				}
}