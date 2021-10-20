package it.beije.herse.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.shop.entity.User;
import it.beije.herse.shop.repository.UserRepository;

@Service
public class LoginService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findByEmailAndPassword(User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		
		List<User> u = userRepository.findByEmailAndPassword(email, password);
		if(u.size()>0) return u.get(0);
		else return null;
	}
	
	public Boolean login(User user) {
		if(findByEmailAndPassword(user)!=null) return true;
		else return false;
	}
	
	public User save(User user) {
		return userRepository.save(user);
	}
}
