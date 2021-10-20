package it.beije.herse.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.beije.herse.shop.entity.Cart;
import it.beije.herse.shop.entity.Product;
import it.beije.herse.shop.repository.ProductRepository;

@Service
public class NewOrderService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAllProducts(){
		return productRepository.findAll();
	}

	public void getProducts(Model model) {
		model.addAttribute("products", findAllProducts());
	}

	public Cart createCart(HttpServletRequest request) {
		Cart cart = new Cart();
		List<Product> products = findAllProducts();
		
		for(Product p : products) {
			String check = (String) request.getParameter("check"+p.getId());
			if(check!=null && check.equalsIgnoreCase("on")) {
				Integer quantity = Integer.valueOf(request.getParameter("quantity"+p.getId()));
				if(quantity>0) cart.addQuantity(p.getId(), quantity);
			}
		}
		
		return cart;
	}
}
