package it.beije.herse.my.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.bean.Users;
import it.beije.herse.entity.User;
import it.beije.herse.my.repository.MyUserRepository;

@Service
public class MyUserService {

	@Autowired
	private MyUserRepository myUserRepository;

	public List<Users> findAll() {
		return myUserRepository.findAll();
	}

	public Users findByEmailAndPassword(String email, String password) {
		Users u = myUserRepository.findByEmailAndPassword(email, password);
		return u;
	}

	public Users findByEmail(String email) {
		Users u = myUserRepository.findByEmail(email);
		return u;
	}

	public Users save(Users users) {
		return myUserRepository.save(users);
	}

	public Users findById(Integer id) {
		Optional<Users> u = myUserRepository.findById(id);
		Users user = u.isPresent() ? u.get() : null;
		return user;
	}

	public Users updateUser(Users users, User newData) {

		BeanUtils.copyProperties(newData, users, new String[] { "id" });

		return users;
	}
	
	public boolean deleteUser(Users user) {
		myUserRepository.delete(user);
		return true;
	}
}
