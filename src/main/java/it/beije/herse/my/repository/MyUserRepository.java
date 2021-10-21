package it.beije.herse.my.repository;



//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import it.beije.herse.bean.Users;

public interface MyUserRepository extends JpaRepository<Users, Integer> {
	public Users findByEmail(String email);
//	public List<Users> findByPassword(String password);
	public Users findByEmailAndPassword(String email, String password);
}
