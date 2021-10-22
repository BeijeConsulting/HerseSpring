package it.beije.herse.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.beije.herse.shop.entity.ShopOrder;


@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {
	List<ShopOrder> findByUserId(Integer userId);
	
//	@Query(nativeQuery = true, 
//			value = "Select o.id, i.id From `order` o Join order_item i On o.id=i.order_id where user_id=:userId")
//	"ReturnType" orderHistory(@Param("userId") Integer userId);
}
