package it.beije.herse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByEmail(String email);
	
	public List<User> findByName(String name);

	public List<User> findByEmailAndPassword(String email, String password);
	

	@Query(value = "SELECT u.id FROM User u")
	public List<Integer> listIds();

	public List<User> findByNameAndSurname(String name, String surname);
	
		
}
