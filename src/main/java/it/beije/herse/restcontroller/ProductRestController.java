package it.beije.herse.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	
	@GetMapping(path = "/product/list")
	public List<Product> getListProducts(){
		List<Product> products = productService.findAll();
		return products;
	}
	
	@GetMapping(path="/product/detail/{id}")
	public Product getProduct(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
	
	@PostMapping("/product/update/{id}")
	public Product updateProduct(@PathVariable("id") Integer id, @RequestBody Product newProduct) {
		if(newProduct.getId()!= null && newProduct.getId().compareTo(id) !=0)
			throw new RuntimeException("id non corrispondente");
		
		Product product = productService.findById(id);
		productService.update(product, newProduct);
		productService.save(product);
		
		return product;
	}
	
	@DeleteMapping("/product/delete/{id}")
	public boolean deleteProduct (@PathVariable("id") Integer id) {
		Product p = productService.findById(id);
		if(p==null)
			return false;
		else {
			productService.deleteProduct(p);
			return true;
		}
	}
	
}
