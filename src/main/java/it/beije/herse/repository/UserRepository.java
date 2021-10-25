package it.beije.herse.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.beije.herse.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public List<User> findByEmail(String email);
	
	public List<User> findByName(String name);
	
	@Query(value = "SELECT u.id FROM User u")
	public List<Integer> listIds();
	
	@Query(value = "select u from User as u where u.name = :name and u.surname = :surname and u.email = :email")
	public List<User> searchUser (@Param("name") String name, @Param("surname") String surname,
			@Param("email") String email);
	
	public List<User> findByNameAndSurname (String name, String surname);
	
	public List<User> findByNameAndEmail (String name, String email);
	
	public List<User> findBySurnameAndEmail(String surname, String email);
	
	public List<User> findBySurname(String surname);
	
}
