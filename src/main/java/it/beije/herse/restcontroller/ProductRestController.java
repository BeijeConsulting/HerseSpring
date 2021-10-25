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


	//@Controller
		@RestController
		@RequestMapping("api")
public class ProductRestController {

	

		@Autowired
		private ProductService productService;

	
		@GetMapping(path = "/product/list")
		public List<Product> getListProducts() {

			List<Product> products = productService.productList();

			System.out.println("product size : " + products.size());

			return products;
		}

		@GetMapping("/product/detail/{id}")
		public Product getUser(@PathVariable("id") Integer id) {

			return productService.findById(id);

		}

		@PostMapping("/product/insert")
		public Product insert(@RequestBody Product product) {
			System.out.println("product : " + product);
			productService.save(product);

			return product;
		}

		@PutMapping("/product/update/{id}")
		public Product update(@PathVariable("id") Integer id, @RequestBody Product newProduct) {

			if (newProduct.getId() != null && newProduct.getId().compareTo(id) != 0) 
				throw new RuntimeException("id non corrispondente");

			Product product = productService.findById(id);
			productService.updateProduct(product, newProduct);
			productService.save(product);

			return product;
		}

		@DeleteMapping("/product/delete/{id}")
		public String deleteUser(@PathVariable("id") Integer id) {

			String fdbk = "impossibile eliminare prodotto";
			boolean del = productService.removeProduct(id);

			if(del) fdbk = "prodotto eliminato";

			return fdbk;

		}
	}

