package it.beije.herse.shop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.shop.entity.ShopOrder;
import it.beije.herse.shop.entity.ShopOrderItem;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.repository.ShopOrderItemRepository;
import it.beije.herse.shop.repository.ShopOrderRepository;
import it.beije.herse.shop.repository.ShopProductRepository;

@Service
public class NewOrderService {

	@Autowired
	private ShopProductRepository productRepository;
	
	@Autowired
	private ShopOrderRepository orderRepository;
	
	@Autowired
	private ShopOrderItemRepository orderItemRepository;
	
	// READ PRODUCT (find all)
	public List<ShopProduct> findAllProducts(){
		return productRepository.findAll();
	}
	
	// READ PRODUCT (find by id)
	public ShopProduct findProductById(Integer prodId) {
		Optional<ShopProduct> p = productRepository.findById(prodId);
		return (p!=null && p.isPresent()) ? p.get() : null;
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
//	public ShopOrder confirmOrder(Cart cart) {
//		ShopOrder order = cart.getOrder();
//		order.setItems(cart.getItems());
//		order.setAmount(cart.getTotal());
//		order.setDateTime(LocalDateTime.now());
//		
//		orderRepository.save(order);
//		
//		return order;
//	}
	
	// CREATE ORDER (CREATE ORDER ITEM)
	public ShopOrder createOrder(List<ShopOrderItem> items, Integer userId) {
		ShopOrder order = new ShopOrder();
			
		order.setUserId(userId);
//		order.setItems(items);
		order.setAmount(0.0);
		order.setDateTime(LocalDateTime.now());
		orderRepository.save(order);
		
		for(ShopOrderItem i : items) i.setOrderId(order.getId());
		order.setItems(items);
		order.setAmount();
		
		return orderRepository.save(order);
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
	
	// UDATE ORDER (DELETE ORDER ITEM)
	public ShopOrder removeItem(Integer remove) {
		Optional<ShopOrderItem> i = orderItemRepository.findById(remove);
		ShopOrderItem item = null;
		if(i!=null && i.isPresent()) item = i.get();
		else throw new IllegalArgumentException("Not Valid OrderItem");
		
		ShopOrder order = orderRepository.findById(item.getOrderId()).get();
		List<ShopOrderItem> orderItems = order.getItems();
		
		orderItems.remove(item);
		order.setAmount();
		orderItemRepository.delete(item);
		
		return orderRepository.save(order);
	}
	
	// UDATE ORDER (UPDATE ORDER ITEM)
	public ShopOrder updateItem(Integer itemId, ShopOrderItem updates) {
		Optional<ShopOrderItem> i = orderItemRepository.findById(itemId);
		ShopOrderItem item = null;
		if(i!=null && i.isPresent()) item = i.get();
		else throw new IllegalArgumentException("Not Valid OrderItem");
		
		ShopOrder order = orderRepository.findById(item.getOrderId()).get();
		List<ShopOrderItem> orderItems = order.getItems();
		Integer itemIndex = orderItems.indexOf(item);
		
		BeanUtils.copyProperties(updates, item, "id");
		orderItems.set(itemIndex, item);
		order.setAmount();
		orderItemRepository.save(item);
		
		return orderRepository.save(order);
	}
	
	// DELETE ORDER 
	public ShopOrder deletOrder(Integer orderId) {
		Optional<ShopOrder> o = orderRepository.findById(orderId);
		ShopOrder order = null;
		if(o!=null && o.isPresent()) order = o.get();
		
		orderRepository.delete(order);
		
		return order;
	}
	
	
//	// CREATE CART
//	public Cart createCart(Map<Integer, Integer> quantities, Integer userId) {
//		Cart cart = new Cart();
//		
//		cart.getOrder().setId(userId);
//		cart.setQuantities(quantities);
//		
//		return cart;
//	}
//	
//	// ADD ITEMS
//	public Cart addToCart(Cart cart) {
//		
//		Map<Integer,Integer> quantities = cart.getQuantities();
//		for(Integer prodId : quantities.keySet()) {
//			ShopOrderItem item = new ShopOrderItem();
//			item.setProductId(prodId);
//			item.setQuantity(quantities.get(prodId));
//			item.setSellPrice(getProductPrice(prodId));
//			
//			cart.addItem(item);
//		}
//		
//		return cart;
//	}
}
