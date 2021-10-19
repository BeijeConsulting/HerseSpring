package it.beije.herse.shop.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.beije.herse.entity.User;
import it.beije.herse.shop.repository.UserRepository;

@Controller
public class UserManager {

	@Autowired
	private UserRepository userRepository;
	
//	TODO
//	-> login user
//	-> signin
	
	public void test() {
		userRepository.findByEmailAndPassword("nv@gmail.com", "nv");
	}
}
