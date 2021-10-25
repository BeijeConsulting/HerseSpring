package it.beije.herse.my.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.bean.Products;
import it.beije.herse.my.repository.MyProductsRepository;

@Service
public class MyProductsService {

	@Autowired
	private MyProductsRepository myProductsRepository;

	public List<Products> findAll() {
		List<Products> products = myProductsRepository.findAll();
		return products;
	}

	public Products findById(Integer id) {
		Optional<Products> product = myProductsRepository.findById(id);
		return product.isPresent() ? product.get() : null;
	}

	public Products updateProducts(Products product, Products newProducts) {
		BeanUtils.copyProperties(newProducts, product, new String[] { "id" });
		return product;

	}

	public void save(Products product) {
		myProductsRepository.save(product);
	}

	public boolean deleteOrder(Products products) {
		myProductsRepository.delete(products);
		return true;
	}

}
