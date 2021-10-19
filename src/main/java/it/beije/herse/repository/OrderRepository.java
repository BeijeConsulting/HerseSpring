package it.beije.herse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
