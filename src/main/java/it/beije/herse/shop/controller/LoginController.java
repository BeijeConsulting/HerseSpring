package it.beije.herse.shop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.shop.entity.User;
import it.beije.herse.shop.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index() {
		return "login/login";
	}
	
	@RequestMapping(path = "/login/login", method = RequestMethod.POST)
	public String login(HttpSession session, @Validated User user) {
		if(loginService.login(user)) {
			User loginUser = loginService.findByEmailAndPassword(user);
			session.setAttribute("loggedUser", loginUser);
			return "user/usermenu";
		}
		else {
			session.setAttribute("signinUser", user);
			return "login/failedlogin";
		}
	}
	
	@RequestMapping(path = "/login/fail", method = RequestMethod.POST)
	public String fail(HttpSession session, Model model, @RequestParam String failedLoginAction) {
		if(failedLoginAction.equalsIgnoreCase("signIn")) {
			User user = (User) session.getAttribute("signinUser");
			loginService.save(user);
			model.addAttribute("loginMessage", "SIGNED IN");
		}
		else model.addAttribute("loginMessage", "RETRY");
		
		return "login/login";
	}
}
