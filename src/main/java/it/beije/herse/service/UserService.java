package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	public List<User> searchUser (@RequestParam(required=false) String name, @RequestParam(required=false) String surname,
			@RequestParam(required=false) String email) {
		
		List<User> users;
		
		if(name != null && surname != null && email != null)
			users = userRepository.searchUser(name, surname, email);
		else if (name != null && surname != null && email == null)
			users = userRepository.findByNameAndSurname(name, surname);
		else if (name != null && surname == null && email == null)
			users = userRepository.findByName(name);
		else if (name == null && surname != null && email != null)
			users = userRepository.findBySurnameAndEmail(surname, email);
		else if (name != null && surname == null && email != null)
			users = userRepository.findByNameAndEmail(name, email);
		else if (name == null && surname != null && email == null)
			users = userRepository.findBySurname(surname);
		else
			users = userRepository.findByEmail(email);
			
		return users;
		
	}
	
	public User updateUser(User user, User newData) {
		
		BeanUtils.copyProperties(newData, user, new String[]{"id"});
		
		return user;
	}
	
	public void deleteUser(User user) {
		
		userRepository.delete(user);
		
	}
	
}
