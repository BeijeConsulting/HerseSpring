package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Product;
import it.beije.herse.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product findById(Integer id) {
		
		Optional<Product> p = productRepository.findById(id);
		
		return p.isPresent() ? p.get() : null;
		
	}
	
	public List<Product> getListProduct () {
		
		return productRepository.findAll();
		
	}

	public Product save (Product product) {
		
		return productRepository.save(product);
		
	}
	
	public Product updateProduct(Product product, Product newData) {
		
		BeanUtils.copyProperties(newData, product, new String[]{"id"});
		
		return product;
		
	}
	
	public void deleteProduct(Product product) {
		
		productRepository.delete(product);	
		
	}
	
	public List<Product> searchProduct (@RequestParam(required = false) String name, @RequestParam(required = false) String description) {
		
		List<Product> products;
		
		if(name != null && description != null)
			products = productRepository.findByNameAndDescription(name, description);
		else if (name != null && description == null)
			products = productRepository.findByName(name);
		else if (name == null && description != null)
			products = productRepository.findByDescription(description);
		else
			products = null;
		
		return products;
		
	}
	
}
