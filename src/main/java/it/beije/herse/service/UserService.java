package it.beije.herse.service;

import java.util.List;
import java.util.Optional;


import javax.servlet.http.HttpSession;

import it.beije.herse.entity.User;

import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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

	public User findByEmail(String email) {
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
	
	public boolean removeUser(Integer id) {
		
		if(userRepository.findById(id)!= null) {
		
		  User u = userRepository.findById(id).get();
		  userRepository.delete(u);
		  
			return true;
			
		} else return false;
	}

	public String[] loginService(HttpSession session, String user, String pass) {
		
		String[] login = new String[2];
		
		User u = userRepository.findByEmail(user);
		String feedback = "";
		
		if(u!=null) {
		
			if(pass.equals(u.getPassword())) {
				
				session.setAttribute("user", user);
				
				login[0]="benvenuti";
				login[1]="";
				
				return login;
				
			} else {
				
				feedback = "la password non corrisponde";
			}
		
		} else {
			
			feedback = "questo nome utente non esiste";
			
		}
		
		login[0]="home";
		login[1]=feedback;
		
		return login;
	}	
		

	public User updateUser(User user, User newData) {
		
		BeanUtils.copyProperties(newData, user, new String[]{"id"});
		
		return user;

	}
	
}
