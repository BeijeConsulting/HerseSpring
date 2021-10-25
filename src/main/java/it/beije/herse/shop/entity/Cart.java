package it.beije.herse.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private ShopOrder order;
	private List<ShopOrderItem> items;
	private Map<Integer,Integer> quantities;
//	private Integer quantities[];
	
	public Cart() {
		order = new ShopOrder();
		items = new ArrayList<ShopOrderItem>();
		quantities = new HashMap<Integer, Integer>();
//		List<ShopProduct> p = ProductManager.selectProducts();
//		quantities = new Integer[p.size()+1];
	}

	public ShopOrder getOrder() {
		return order;
	}
	public void setOrder(ShopOrder order) {
		this.order = order;
	}
	public void setOrderUserId(Integer userId) {
		order.setUserId(userId);
	}
	public void setOrderDateTime(LocalDateTime dateTime) {
		order.setDateTime(dateTime);
	}

	public List<ShopOrderItem> getItems() {
		return items;
	}
	public void setItems(List<ShopOrderItem> items) {
		this.items = items;
	}
	public void addItem(ShopOrderItem item) {
		items.add(item);
	}
	public void removeItem(ShopOrderItem item) {
		items.remove(item);
	}
	public Double getTotal() {
		Double total = 0.0;
		
		for(ShopOrderItem i : items) total += i.getSellPrice()*i.getQuantity();
		
		return total;
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
