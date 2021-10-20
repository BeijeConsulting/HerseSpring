package it.beije.herse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	@Query(value = "SELECT oi FROM OrderItem AS oi LEFT OUTER JOIN Order AS o ON o.id = oi.orderId WHERE o.userId = :user_id AND oi.orderId <> NULL ORDER BY oi.orderId")
	public List<OrderItem> search(@Param("user_id") Integer user_id);
}
