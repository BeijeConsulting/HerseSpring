package it.beije.herse.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.beije.herse.service.ProductService;
import it.beije.herse.entity.Product;

@RestController
@RequestMapping("api")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/list")
	public List<Product> getListProduct() {
		
		List<Product> products = productService.findAll();
		System.out.println("products size : " + products.size());
		return products;
	}
}
