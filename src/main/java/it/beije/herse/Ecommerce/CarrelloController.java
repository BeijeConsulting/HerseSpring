package it.beije.herse.Ecommerce;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarrelloController {
	
	@RequestMapping(path = "carrello/crea", method = RequestMethod.POST)
	public String addToCart(HttpSession session, Model model, @RequestParam String productId, @RequestParam String quantity) {
		System.out.println("sono in carrello/crea");
		
//		String user = (String)  session.getAttribute("userId");
//		
//		System.out.println("userId " + user);
//		
//		int userId = Integer.parseInt(user);
//		int prodId = Integer.parseInt(productId);
//		int quant = Integer.parseInt(quantity);
//		
//		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
//		
//		Shop shop = new Shop();
//		
//		if(shop.productExists(prodId)) {
//			if(shop.checkQuantity(prodId, quant, userId)) {
//				shop.addCart(map);
//			}
//		}
//		
		LoginController login = new LoginController();	
		
		return "redirect:/loginShop";
	}
	
}
