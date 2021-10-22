package it.beije.herse.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.beije.herse.shop.entity.Cart;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.repository.ShopProductRepository;

@Service
public class NewOrderService {

	@Autowired
	private ShopProductRepository productRepository;
	
	public List<ShopProduct> findAllProducts(){
		return productRepository.findAll();
	}

	public Cart createCart(HttpServletRequest request) {
		Cart cart = new Cart();
		List<ShopProduct> products = findAllProducts();
		
		for(ShopProduct p : products) {
			String check = (String) request.getParameter("check"+p.getId());
			if(check!=null && check.equalsIgnoreCase("on")) {
				Integer quantity = Integer.valueOf(request.getParameter("quantity"+p.getId()));
				if(quantity>0) cart.addQuantity(p.getId(), quantity);
			}
		}
		
		return cart;
	}
}
