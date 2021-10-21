package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

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

}
