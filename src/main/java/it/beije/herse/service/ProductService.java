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
		Optional <Product> p = productRepository.findById(id);
		return p.isPresent() ? p.get() : null;
	}
	
	public void removeQuantity(Integer prodId, Integer newQuantity) {
//		return productRepository.updateProduct(newQuantity, prodId);
////		System.out.println("prodId: " + prodId);
////		System.out.println("newQuantity: " + newQuantity);
//
		Optional <Product> prod = productRepository.findById(prodId);
		Product p = prod.get();
		System.out.println("PRE: " + p);
		if (p != null) {
			p.setQuantity(newQuantity);
			productRepository.save(p);
		}
		System.out.println("POST: " + p);
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product, Product newProduct) {
		BeanUtils.copyProperties(newProduct, product, new String[] {"id"});
		return product;
	}
	
	public boolean delete(Product prodToDelete) {
		if(prodToDelete != null) {
			productRepository.delete(prodToDelete);
			return true;
		} else {
			return false;
		}
	}
	
	
}
