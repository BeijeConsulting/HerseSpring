package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.repository.ProductRepository;
import it.beije.herse.entity.Order;
import it.beije.herse.entity.Product;

@Service
public class ProductService {
	
	@Autowired 
	private ProductRepository productRepository;
	
	public Product findById(Integer id) {
		Optional<Product> p = productRepository.findById(id);
		Product product = p.isPresent() ? p.get() : null;

		return product;
	}

	public List<Product> findAll() {
		return (List<Product>)productRepository.findAll();

	}

	public Product update(Product product, Product newProduct) {
	BeanUtils.copyProperties(newProduct, product, new String[] {"id"});
		return product;
	}

	public Product save(Product product) {
		return productRepository.save(product);
		
	}
	
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

}
