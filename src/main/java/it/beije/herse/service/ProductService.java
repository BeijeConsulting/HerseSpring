package it.beije.herse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product findById(Integer id) {
		Optional<Product> p = productRepository.findById(id);
		return p.isPresent() ? p.get() : null;
	}
	
	public List<Product> findAll() {
		List<Product> list = productRepository.findAll();
		return list;
	}
}
