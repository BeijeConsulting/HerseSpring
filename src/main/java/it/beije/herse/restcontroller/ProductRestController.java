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
import it.beije.herse.service.ProductService;
import it.beije.herse.entity.Product;

@RestController
@RequestMapping("api")
public class ProductRestController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/product/list")
	public List<Product> getListProduct() {
		
		List<Product> products = productService.findAllProducts();
		System.out.println("products size : " + products.size());
		return products;
	}
	
	@PostMapping("/product/insert")
	public Product insert(@RequestBody Product product) {
		productService.saveProduct(product);
		return product;
	}
	
	@PutMapping("/product/update/{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product newProduct) {
		
		if (newProduct.getId() != null && newProduct.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");
		
		Product product = productService.findById(id);
		productService.updateProduct(product, newProduct);
		productService.saveProduct(product);
		
		return newProduct;
	}
	
	@DeleteMapping("/product/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
	}
}
