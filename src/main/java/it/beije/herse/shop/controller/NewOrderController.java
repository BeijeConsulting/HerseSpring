package it.beije.herse.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.shop.entity.Cart;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.service.NewOrderService;

@Controller
public class NewOrderController {
	
	@Autowired
	private NewOrderService newOrderService;
	
	@RequestMapping(path = "/order/backToMenu", method = RequestMethod.POST)
	public String backToMenu() {
		return "order/neworder";
	}

	@RequestMapping(path = "/order/menu", method = RequestMethod.POST)
	public String menu(HttpServletRequest request, HttpSession session, Model model) {
		
		Cart cart = newOrderService.createCart(request);
		
		String back = (String) request.getParameter("backToMenu");
		if(back!=null && back.equalsIgnoreCase("BACK TO MENU")) return "/user/usermenu";
		
		List<ShopProduct> products = newOrderService.findAllProducts();
		for(ShopProduct p : products) {
			String details = (String) request.getParameter("prodDetails"+p.getId());
			if(details!=null && details.equalsIgnoreCase(p.getName())) {
				model.addAttribute("product", p);
				return "order/productdetails";
			}
		}
		
		//newOrderService.addToCart(cart, )
		
		return "";
	}
}
