package it.beije.herse.test.api;

import java.beans.Beans;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.repository.ShopUserRepository;

@Service
public class TestService {

	@Autowired
	private ShopUserRepository shopUserRepository;
	
	public ShopUser getLoginUser(String email, String password) {
		ShopUser u = null;
		List<ShopUser> users = shopUserRepository.findByEmailAndPassword(email, password);
		if(users!=null && users.size()>0) u = users.get(0);
		return u;
	}
	
	public ShopUser getUserById(Integer id) {
		ShopUser u = null;
		Optional<ShopUser> users = shopUserRepository.findById(id);
		if(users!=null && users.isPresent()) u = users.get();
		return u;
	}
	
	public ShopUser saveUser(ShopUser user) {
		ShopUser u = null;
		if(user!=null) u = shopUserRepository.save(user);
		return u;
	}
	
	public ShopUser updateUser(ShopUser updates, Integer userId) {
		ShopUser u = getUserById(userId);
		if(u!=null) {
			BeanUtils.copyProperties(updates, u, "id");
			saveUser(u);
		}
		return u;
	}
	
	public ShopUser deleteUser(Integer userId) {
		ShopUser u = getUserById(userId);
		if(u!=null) shopUserRepository.delete(u);
		return u;
	}
}
