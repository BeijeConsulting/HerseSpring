package it.beije.herse.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	public List<Order> findByDateTimeGreaterThan(LocalDateTime dateTime);
	
//	@Query(nativeQuery = true, value = "SELECT * FROM `order` WHERE creation_datetime > :dateTime")
	@Query(value = "SELECT o FROM Order as o WHERE o.dateTime > :dateTime")
	public List<Order> searchByDateTimeGreaterThan(@Param("dateTime") LocalDateTime dateTime);

	
    public List<Order> findAll();
	

	@Query(value = "SELECT o FROM Order as o JOIN FETCH o.items")
	public List<Order> listOrdersWithItems();


}
