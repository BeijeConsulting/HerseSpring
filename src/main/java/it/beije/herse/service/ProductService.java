package it.beije.herse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.Product;
import it.beije.herse.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	
	public List<Product> productList(){
		
	    return productRepository.findAll();
	}
	
	
	
}
