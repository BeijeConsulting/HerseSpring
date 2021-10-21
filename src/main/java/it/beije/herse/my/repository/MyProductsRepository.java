package it.beije.herse.my.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import it.beije.herse.bean.Products;

public interface MyProductsRepository extends JpaRepository<Products, Integer> {
	public List<Products> findAll();
}
