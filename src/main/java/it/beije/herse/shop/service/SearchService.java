package it.beije.herse.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.repository.ShopUserRepository;

@Service
public class SearchService {
	
	@Autowired
	private ShopUserRepository userRepository;

	public List<ShopUser> searchUsers(List<String> parameters) {
		return searchUsers(userRepository.findAll(), parameters);
	}

	private List<ShopUser> searchUsers(List<ShopUser> users, List<String> parameters) {
		if(parameters.size()>0) {
//			switch(parameters.get(0)) {
//			case
//			}
		}
		return users;
	}
}
