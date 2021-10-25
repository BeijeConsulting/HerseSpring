package it.beije.herse.shop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.shop.entity.ShopOrder;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.service.NewOrderService;
import it.beije.herse.shop.service.UserMenuService;

@RestController
@RequestMapping("shop/api/")
public class UserMenuRestController {

	@Autowired
	private UserMenuService userMenuService;
	
	@GetMapping("user/profile/{id}")
	public ShopUser showProfile(@PathVariable("id") Integer userId) {
		return userMenuService.findUserById(userId);
	}
	
	@PutMapping("user/update/{id}")
	public ShopUser updateProfile(@RequestBody ShopUser updates,
			@PathVariable("id") Integer userId) {
		if(updates.getId()!=null && updates.getId().compareTo(userId)!=0) throw new IllegalArgumentException();
		
		ShopUser loggedUser = userMenuService.findUserById(userId);
		return userMenuService.updateUser(loggedUser, updates);
	}
	
	@GetMapping("user/orderhistory/{id}")
	public List<ShopOrder> showOrderHistory(@PathVariable("id") Integer userId) {
		return userMenuService.getOrderHistory(userId);
	}
	
	@PostMapping("user/sellproduct")
	public ShopProduct sellProduct(@RequestBody ShopProduct product) {
		return userMenuService.sellProduct(product);
	}
	
}
