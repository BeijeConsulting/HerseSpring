package it.beije.herse.test.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.shop.entity.ShopUser;

@RestController
@RequestMapping("test/api")
public class TestRestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping("login/{email}&{password}")
	public ShopUser testLogin(@PathVariable("email") String email, 
			@PathVariable("password") String password) {
		
		return testService.getLoginUser(email, password);
	}
	
	@PostMapping("signin")
	public ShopUser testSignin(@RequestBody ShopUser user) {
		return testService.saveUser(user);
	}
	
	@PutMapping("update/{id}")
	public ShopUser testUpdate(@RequestBody ShopUser updates,
			@PathVariable("id") Integer id) {
		if(updates.getId()!=null && updates.getId().compareTo(id)!=0) throw new IllegalArgumentException();
		
		return testService.updateUser(updates, id);
	}
	
	@DeleteMapping("delete/{id}")
	public ShopUser testDelete(@PathVariable("id") Integer id) {
		return testService.deleteUser(id);
	}
}
