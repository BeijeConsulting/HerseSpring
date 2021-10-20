package it.beije.herse.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.shop.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
