package it.beije.herse.shop.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.OrderItem;
import it.beije.herse.shop.entity.ShopOrder;
import it.beije.herse.shop.entity.ShopOrderItem;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.service.NewOrderService;

@RestController
@RequestMapping("shop/api/")
public class NewOrderRestController {
	
	@Autowired
	private NewOrderService newOrderService;

	@GetMapping("products/all")
	public List<ShopProduct> showProductsCatalog(){
		return newOrderService.findAllProducts();
	}
	
	@GetMapping("products/detail/{prodId}")
	public ShopProduct showProductDetails(@PathVariable("prodId") Integer prodId) {
		return newOrderService.findProductById(prodId);
	}
	
	@PostMapping("order/additems/{userId}")
	public ShopOrder addToCart(@RequestBody List<ShopOrderItem> items, 
			@PathVariable("userId") Integer userId) {
		
		return newOrderService.createOrder(items, userId);
	}
	
}
