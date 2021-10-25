package it.beije.herse.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Product;

    @Repository
	public interface ProductRepository extends JpaRepository<Product, Integer> {
		
		public List<Product> findAll();
		public Product findByName(String name);
		
		@Query(value = "SELECT p FROM Product as p WHERE p.name LIKE :nome%")
		public List<Product> findBy_LIKE_Name(@Param("nome")String nome);
	}
