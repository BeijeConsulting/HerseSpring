package it.beije.herse.my.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.bean.Users;
import it.beije.herse.entity.User;
import it.beije.herse.my.service.MyUserService;

@RestController
@RequestMapping("api")
public class MyUserRestController {

	@Autowired
	private MyUserService myUserService;

//	@GetMapping("/users/detail/{email,password}")
//	public Users getAuthUser(@PathVariable("email") String email, @PathVariable("password") String password) {
//		Users user = myUserService.findByEmailAndPassword(email, password);
//		return user;	
//	}

	@GetMapping("/users/detail/{email}")
	public Users getUser(@PathVariable("email") String email) {
		
		Users user = myUserService.findByEmail(email);
		return user;
	}

	@GetMapping(path = "/users/detail")
	public List<Users> getListUsers() {

		List<Users> users = myUserService.findAll();
		System.out.println("users size : " + users.size());

		return users;
	}
	
	@PostMapping("/users/insert")
	public Users insert(@RequestBody Users users) {
		System.out.println("users : " + users);
		myUserService.save(users);
		
		return users;
	}
	
	@PutMapping("/users/update/{id}")
	public Users update(@PathVariable("id") Integer id, @RequestBody User newUser) {
		
		if (newUser.getId() != null && newUser.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");
		
		Users user = myUserService.findById(id);
		myUserService.updateUser(user, newUser);
		myUserService.save(user);
		
		return user;
	}
	
	@PutMapping("/users/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		Users user = myUserService.findById(id);
		String response;
		if(user != null) {
			response = myUserService.deleteUser(user) ? "deleted" : "not deleted";
			System.out.println(response);
		}else {
			System.out.println("User not found");
		}
	}
}
