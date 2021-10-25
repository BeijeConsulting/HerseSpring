package it.beije.herse.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.UserService;


//@Controller
@RestController
@RequestMapping("api")
public class UserRestController {

	@Autowired
	private UserService userService;
	
//	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
//	public @ResponseBody List<User> getListUsers() {
	@GetMapping(path = "/user/list")
	public List<User> getListUsers() {
		
		List<User> users = userService.findAll();
		
		System.out.println("users size : " + users.size());
		
		return users;
	}
	
	@GetMapping("/user/detail/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		
		return userService.findById(id);
		
	}
	
	@PostMapping("/user/insert")
	public User insert(@RequestBody User user) {
		System.out.println("user : " + user);
		userService.save(user);
		
		return user;
	}

	@PutMapping("/user/update/{id}")
	public User update(@PathVariable("id") Integer id, @RequestBody User newUser) {
		
		if (newUser.getId() != null && newUser.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");
		
		User user = userService.findById(id);
		userService.updateUser(user, newUser);
		userService.save(user);
		
		return user;
	}
	
	@DeleteMapping("/user/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {

		userService.deleteUser(userService.findById(id));
		
	}

}
