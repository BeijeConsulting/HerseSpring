package it.beije.herse.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.User;
import it.beije.herse.shop.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/user/backToMenu", method = RequestMethod.POST)
	public String backToMenu() {
		return "user/usermenu";
	}
	
	@RequestMapping(path = "/user/menu", method = RequestMethod.POST)
	public String menu(@RequestParam(required = false) String exit, 
			@RequestParam(required = false) String userAction, Model model) {
		if(exit==null) {
			switch(userAction) {
			case "showProfile":
				return "user/userprofile";
			case "showOrderHistory":
				return "user/orderhistory";
			case "newOrder":
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
	public String update(HttpSession session, @Validated User user) {
		System.out.println("USER: "+user);
		User loggedUser = (User) session.getAttribute("loggedUser");
		
		if(user.getName()!=null && user.getName().length()>0) loggedUser.setName(user.getName());
		if(user.getSurname()!=null && user.getSurname().length()>0) loggedUser.setSurname(user.getSurname());
		if(user.getEmail()!=null && user.getEmail().length()>0) loggedUser.setEmail(user.getEmail());
		if(user.getPassword()!=null && user.getPassword().length()>0) loggedUser.setPassword(user.getPassword());
		
		userRepository.save(loggedUser);
		session.setAttribute("loggedUser", loggedUser);
		
		return "user/usermenu";
	}
	
}
