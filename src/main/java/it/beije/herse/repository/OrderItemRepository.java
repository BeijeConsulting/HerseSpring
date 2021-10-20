package it.beije.herse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.beije.herse.entity.OrderItem;

public interface OrderItemRepository  extends JpaRepository<OrderItem, Integer> {
	
	@Query(value = "SELECT i FROM Order o, OrderItem i WHERE o.id=i.orderId")
	public List<OrderItem> elencoOrderOrderItems();

}
