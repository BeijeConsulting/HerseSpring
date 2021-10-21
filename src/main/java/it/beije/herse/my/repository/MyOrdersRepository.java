package it.beije.herse.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.beije.herse.bean.Orders;
import java.util.List;

public interface MyOrdersRepository extends JpaRepository<Orders, Integer> {
	public List<Orders> findByUserId(Integer userId);
}
