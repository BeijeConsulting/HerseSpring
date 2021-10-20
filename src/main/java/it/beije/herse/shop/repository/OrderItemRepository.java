package it.beije.herse.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.shop.entity.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
	
	List<OrderItem> findByOrderId(Integer orderId);
}
