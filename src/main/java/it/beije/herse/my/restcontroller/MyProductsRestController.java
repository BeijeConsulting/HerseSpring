package it.beije.herse.my.restcontroller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.bean.Orders;
import it.beije.herse.bean.Products;
import it.beije.herse.my.repository.MyProductsRepository;
import it.beije.herse.my.service.MyProductsService;

@RestController
@RequestMapping("api")
public class MyProductsRestController {

	@Autowired
	private MyProductsService myProductsService;

	@GetMapping("/products/detail")
	public List<Products> getOrders() {
		List<Products> products = myProductsService.findAll();
		if (products.size() == 0) {
			System.out.println("non ci sono ordini");
			return null;
		}
		return products;
	}

	@GetMapping("/products/detail/{id}")
	public Products getProduct(@PathVariable("id") Integer id) {
		Products product = myProductsService.findById(id);

		return product;
	}

	@PostMapping("/products/insert")
	public Products insert(@RequestBody Products products) {
		System.out.println("Order: " + products);

		myProductsService.save(products);

		return products;
	}

	@PutMapping("/products/update/{id}")
	public Products update(@PathVariable("id") Integer id, @RequestBody Products newProducts) {
		if (newProducts.getId() != null && newProducts.getId() != 0) {
			throw new RuntimeException("id non corrispondente");
		}
		Products product = myProductsService.findById(id);
		myProductsService.updateProducts(product, newProducts);
		myProductsService.save(product);

		return product;
	}
	
	@PutMapping("/products/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		Products products = myProductsService.findById(id);
		String response;
		if(products != null) {
			response = myProductsService.deleteOrder(products) ? "deleted" : "not deleted";
			System.out.println(response);
		}else {
			System.out.println("product not found");
		}
	}
}
