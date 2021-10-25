package it.beije.herse.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.Product;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderItemService orderItemService;
	
	public Product findById(Integer id) {
		Optional<Product> p = productRepository.findById(id);
		return p.isPresent() ? p.get() : null;
	}
	
	public List<Product> findAllProducts() {
		List<Product> list = productRepository.findAll();
		return list;
	}
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Product product, Product newProduct) {
		BeanUtils.copyProperties(newProduct, product,  new String[] { "id" });
		return product;
	}
	
	public void deleteProduct(Integer id) {
		List<OrderItem> listOI = orderItemService.findByProductId(id);
		for (OrderItem o : listOI) {
			orderItemService.deleteOrderItem(o.getId());
		}
		System.out.println("product deleted : " + id);
		productRepository.deleteById(id);
	}
	
	public List<Product> findByPriceGreaterThan(Double price) {
		return productRepository.findByPriceGreaterThan(price);
	}
}
