package it.beije.herse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public Optional<Product> findById(Integer id);
}
