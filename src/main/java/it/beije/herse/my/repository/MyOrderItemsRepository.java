package it.beije.herse.my.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.beije.herse.bean.OrderItems;

public interface MyOrderItemsRepository extends JpaRepository<OrderItems, Integer> {
	public List<OrderItems> findByOrderId(Integer orderId);

}
