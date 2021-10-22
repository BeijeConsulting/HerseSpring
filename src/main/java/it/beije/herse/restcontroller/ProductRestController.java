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
	
	@GetMapping("product/list")
	public List<Product> getProductList(){
		 List<Product> products = productService.findAll();
		 
		 return products;
	}
	
	@PostMapping("/product/insert")
	public Product insert(@RequestBody Product product) {
		System.out.println("product: " + product);
		return productService.save(product);
	}
	
	@PutMapping("product/update/{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product newProduct) {
		if(newProduct.getId() != null  && newProduct.getId().compareTo(id) != 0)
			throw new RuntimeException("id non corrispondente");
		
		Product product = productService.findById(id);
		productService.updateProduct(product, newProduct);
		return productService.save(product);
	}
	
	@DeleteMapping("product/delete/{id}")
	public Boolean delete(@PathVariable("id") Integer id) {
		Product prodToDelete = productService.findById(id);
		return productService.delete(prodToDelete);
	}

}
