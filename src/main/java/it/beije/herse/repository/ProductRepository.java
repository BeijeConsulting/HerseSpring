package it.beije.herse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<User> findByName(String name);
	
}
