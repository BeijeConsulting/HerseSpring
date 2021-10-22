package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	public User save(User user) {
		return userRepository.save(user);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Integer id) {
		Optional<User> u = userRepository.findById(id);
		return u.isPresent() ? u.get() : null;
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}

	public List<User> searchByName(String name) {
		List<User> users;
		if (name != null && name.length() > 0) {
			users = userRepository.findByName(name);
		} else {
			users = userRepository.findAll();		
		}
		
		return users;
	}
	

	public User findByEmailAndPAssword(String email, String password) {
		Optional<User> u = userRepository.findByEmailAndPassword(email, password);
		return u.isPresent() ? u.get() : null;
	}
	
	public User updateUser(User user, User newData) {
		
		BeanUtils.copyProperties(newData, user, new String[]{"id"});
		
		return user;

	}
	
	public void deleteUser(User user) {
		userRepository.delete(user);
	}
	
}
