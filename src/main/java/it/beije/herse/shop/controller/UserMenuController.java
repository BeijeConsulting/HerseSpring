package it.beije.herse.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.service.NewOrderService;
import it.beije.herse.shop.service.UserMenuService;

@Controller
public class UserMenuController {
	
	@Autowired
	private UserMenuService userMenuService;
	
	@Autowired
	private NewOrderService newOrderService;
	
	@RequestMapping(path = "/user/backToMenu", method = RequestMethod.POST)
	public String backToMenu() {
		return "user/usermenu";
	}
	
	@RequestMapping(path = "/user/menu", method = RequestMethod.POST)
	public String menu(@RequestParam(required = false) String exit, @RequestParam(required = false) String userAction, 
			Model model, HttpSession session) {
		if(exit==null) {
			switch(userAction) {
			case "showProfile":
				return "user/userprofile";
			case "showOrderHistory":
				ShopUser loggedUser = (ShopUser) session.getAttribute("loggedUser");
				model.addAttribute("orderHistory", userMenuService.getOrderHistory(loggedUser));
				return "user/orderhistory";
			case "newOrder":
				newOrderService.getProducts(model);
				return "order/neworder";
			case "updateProfile":
				return "user/updateprofile";
			default:
				return "user/usermenu";
			}
		}
		else return "login/login";
	}
	
	@RequestMapping(path = "/user/update", method = RequestMethod.POST)
	public String update(HttpSession session, @Validated ShopUser user) {
		ShopUser loggedUser = (ShopUser) session.getAttribute("loggedUser");
		
		session.setAttribute("loggedUser", userMenuService.updateUser(loggedUser, user));
		
		return "user/usermenu";
	}
	
}
