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
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.Product;
import it.beije.herse.service.ProductService;

@RestController
@RequestMapping("api")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/list")
	public List<Product> getListProduct () {
		return productService.getListProduct();
	}
	
	@PostMapping("/product/insert")
	public Product insert (@RequestBody Product product) {
		return productService.save(product);
	}
	
	@PutMapping("/product/update/{id}")
	public Product update (@PathVariable("id") Integer id, @RequestBody Product newProd) {
		
		if(newProd.getId() != null && newProd.getId().compareTo(id) != 0)
			throw new RuntimeException("L'id non corrisponde");
		
		Product product = productService.findById(id);
		productService.updateProduct(product, newProd);
		
		return productService.save(product);
		
	}

	@DeleteMapping("/product/delete/{id}")
	public void delete (@PathVariable("id") Integer id) {
		productService.deleteProduct(productService.findById(id));
	}
	
}
