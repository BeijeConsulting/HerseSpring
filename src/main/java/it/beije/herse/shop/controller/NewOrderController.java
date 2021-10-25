package it.beije.herse.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import it.beije.herse.shop.entity.Cart;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.entity.ShopUser;
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
		
		List<ShopProduct> products = newOrderService.findAllProducts();
		
		// CREATE CART
		Integer userId = ((ShopUser)session.getAttribute("loggedUser")).getId();
		Map<Integer, Integer> quantities = new HashMap<Integer, Integer>();
		for(ShopProduct p : products) {
			String check = (String) request.getParameter("check"+p.getId());
			if(check!=null && check.equalsIgnoreCase("on")) {
				Integer quantity = Integer.valueOf(request.getParameter("quantity"+p.getId()));
				if(quantity>0) quantities.put(p.getId(), quantity);
			}
		}
		Cart cart = newOrderService.createCart(quantities, userId);
		session.setAttribute("cart", cart);
		
		// BACK TO MENU
		String back = (String) request.getParameter("backToMenu");
		if(back!=null && back.equalsIgnoreCase("BACK TO MENU")) return "/user/usermenu";
		
		// PRODUCT DETAILS
		for(ShopProduct p : products) {
			String details = (String) request.getParameter("prodDetails"+p.getId());
			if(details!=null && details.equalsIgnoreCase(p.getName())) {
				model.addAttribute("product", p);
				return "order/productdetails";
			}
		}
		
		// ADD TO CART
		String addToCart = (String) request.getParameter("addToCart");
		if(addToCart!=null && addToCart.equalsIgnoreCase("ADD TO CART")) {
			newOrderService.addToCart(cart);
			session.setAttribute("cart", cart);
			return "order/checkout";
		}
		
		return "order/neworder";
	}
}
