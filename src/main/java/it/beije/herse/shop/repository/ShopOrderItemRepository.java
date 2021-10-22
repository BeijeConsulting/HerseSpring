package it.beije.herse.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.shop.entity.ShopOrderItem;


@Repository
public interface ShopOrderItemRepository extends JpaRepository<ShopOrderItem, Integer> {
	
	List<ShopOrderItem> findByOrderId(Integer orderId);
}
