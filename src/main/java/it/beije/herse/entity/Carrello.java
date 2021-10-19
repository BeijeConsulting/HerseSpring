package it.beije.herse.entity;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
	
	private List<OrderItem> items = new ArrayList<>();

	public List<OrderItem> getItems() {
		return items;
	}

	public void addItem(OrderItem item) {
		this.items.add(item);
	}
	
	public void setListItem(List<OrderItem> items) {
		this.items = items;
	}
	
}
