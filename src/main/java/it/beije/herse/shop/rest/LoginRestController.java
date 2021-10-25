package it.beije.herse.shop.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.service.LoginService;

@RestController
@RequestMapping("shop/api/")
public class LoginRestController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("home/login")
	public ShopUser login(@RequestBody ShopUser user) {
		return loginService.findByEmailAndPassword(user);
	}
	
	@PostMapping("home/signin")
	public ShopUser signin(@RequestBody ShopUser user) {
		return loginService.signin(user);
	}
}
