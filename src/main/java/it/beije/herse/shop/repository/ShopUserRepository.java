package it.beije.herse.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.beije.herse.shop.entity.ShopUser;


@Repository
public interface ShopUserRepository extends JpaRepository<ShopUser, Integer> {
	List<ShopUser> findByEmailAndPassword(String email, String passsword);
	
	List<ShopUser> findByName(String name);
	List<ShopUser> findBySurname(String surname);
	List<ShopUser> findByEmail(String email);
	List<ShopUser> findByPassword(String password);

}
