package it.beije.herse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> { 
	
//	public List<Product> findAll();
	
	@Query(value = "SELECT p FROM Product as p WHERE p.price > :price")
	public List<Product> searchByProductsMoreExpensive(@Param("price") Double price);
	
}
