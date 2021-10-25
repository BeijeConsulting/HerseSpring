package it.beije.herse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
//	@Modifying
//	@Query(value = "UPDATE Product p SET p.quantity = :newQuantity WHERE p.id = :prodId")
//	public List<Product> removeQuantity(@Param("newQuantity") Integer newQuantity, @Param("prodId") Integer prodId);
	
	@Modifying
	@Query("update Product p set p.quantity = :newQuantity where p.id = :prodId")
	public List<Product> updateProduct(@Param(value = "newQuantity") Integer newQuantity, @Param(value = "prodId") Integer prodId);
	
	public List<Product> findByNameAndDescription(String name, String description);
}
