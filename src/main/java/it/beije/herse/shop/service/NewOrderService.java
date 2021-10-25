package it.beije.herse.shop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.herse.shop.entity.Cart;
import it.beije.herse.shop.entity.ShopOrder;
import it.beije.herse.shop.entity.ShopOrderItem;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.repository.ShopOrderRepository;
import it.beije.herse.shop.repository.ShopProductRepository;

@Service
public class NewOrderService {

	@Autowired
	private ShopProductRepository productRepository;
	
	@Autowired
	private ShopOrderRepository orderRepository;
	
	// READ PRODUCT (find all)
	public List<ShopProduct> findAllProducts(){
		return productRepository.findAll();
	}
	
	public Double getProductPrice(Integer prodId) {
		ShopProduct product = null;
		Optional<ShopProduct> p = productRepository.findById(prodId);
		if(p!=null && p.isPresent()) {
			product = p.get();
			return product.getPrice();
		}
		else throw new IllegalArgumentException("Not Valid prodId");
	}
	
	// CREATE ORDER
	public ShopOrder confirmOrder(Cart cart) {
		ShopOrder order = cart.getOrder();
		order.setItems(cart.getItems());
		order.setAmount(cart.getTotal());
		order.setDateTime(LocalDateTime.now());
		
		orderRepository.save(order);
		
		return order;
	}
	
	// READ ORDER(find all)
	public List<ShopOrder> findAllOrders(){
		return orderRepository.findAll();
	}
	
	// UPDATE ORDER
	public ShopOrder updateOrder(Integer orderId, ShopOrder updates) {
		Optional<ShopOrder> o = orderRepository.findById(orderId);
		ShopOrder order = null;
		if(o!=null && o.isPresent()) order = o.get();
		
		BeanUtils.copyProperties(updates, order, "id");
		
		return order;
	}
	
	// DELETE ORDER 
	public ShopOrder deletOrder(Integer orderId) {
		Optional<ShopOrder> o = orderRepository.findById(orderId);
		ShopOrder order = null;
		if(o!=null && o.isPresent()) order = o.get();
		
		orderRepository.delete(order);
		
		return order;
	}

	// CREATE CART
	public Cart createCart(Map<Integer, Integer> quantities, Integer userId) {
		Cart cart = new Cart();
		
		cart.getOrder().setId(userId);
		cart.setQuantities(quantities);
		
		return cart;
	}
	
	// ADD ITEMS
	public Cart addToCart(Cart cart) {
		
		Map<Integer,Integer> quantities = cart.getQuantities();
		for(Integer prodId : quantities.keySet()) {
			ShopOrderItem item = new ShopOrderItem();
			item.setProductId(prodId);
			item.setQuantity(quantities.get(prodId));
			item.setSellPrice(getProductPrice(prodId));
			
			cart.addItem(item);
		}
		
		return cart;
	}
}
