package it.beije.herse.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private Order order;
	private List<OrderItem> items;
	private Map<Integer,Integer> quantities;
//	private Integer quantities[];
	
	public Cart() {
		order = new Order();
		items = new ArrayList<OrderItem>();
		quantities = new HashMap<Integer, Integer>();
//		List<Product> p = ProductManager.selectProducts();
//		quantities = new Integer[p.size()+1];
	}

	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setOrderUserId(Integer userId) {
		order.setUserId(userId);
	}
	public void setOrderDateTime(LocalDateTime dateTime) {
		order.setDateTime(dateTime);
	}

	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public void addItem(OrderItem item) {
		items.add(item);
	}
	public void removeItem(OrderItem item) {
		items.remove(item);
	}

	public Map<Integer,Integer> getQuantities() {
		return quantities;
	}
	public void setQuantities(Map<Integer, Integer> quantities) {
		this.quantities = quantities;
	}
	public void addQuantity(Integer index, Integer value) {
		quantities.put(index, value);
	}
	public void removeQuantity(Integer index) {
		quantities.remove(index);
	}
//	public Integer[] getQuantities() {
//		return quantities;
//	}
//	public void addQuantity(Integer index, Integer param) {
//		quantities[index] = param;
//	}
//	public void removeQuantity(Integer index) {
//		quantities[index] = null;
//	}
}
