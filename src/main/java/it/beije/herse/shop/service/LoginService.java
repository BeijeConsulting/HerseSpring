package it.beije.herse.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.repository.ShopUserRepository;

@Service
public class LoginService {
	
	@Autowired
	private ShopUserRepository userRepository;
	
	public ShopUser findByEmailAndPassword(ShopUser user) {
		String email = user.getEmail();
		String password = user.getPassword();
		
		List<ShopUser> u = userRepository.findByEmailAndPassword(email, password);
		if(u.size()>0) return u.get(0);
		else return null;
	}
	
	public Boolean login(ShopUser user) {
		if(findByEmailAndPassword(user)!=null) return true;
		else return false;
	}
	
	public ShopUser save(ShopUser user) {
		return userRepository.save(user);
	}
}
