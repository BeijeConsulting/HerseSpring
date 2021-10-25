package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
}
