package it.beije.herse.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.User;
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
}
