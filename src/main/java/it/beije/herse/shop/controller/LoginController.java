package it.beije.herse.shop.controller;

import java.util.List;

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
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String index() {
		return "login/login";
	}
	
	@RequestMapping(path = "/login/login", method = RequestMethod.POST)
	public String login(HttpSession session, @Validated User user) {
		String email = user.getEmail();
		String password = user.getPassword();
		
		List<User> users = userRepository.findByEmailAndPassword(email, password);
		User loginUser = null;
		if(users.size()>0) loginUser = users.get(0);
			
		System.out.println("USER: "+loginUser);
		
		
		if(loginUser!=null) {
			session.setAttribute("loginUsed", loginUser);
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
			userRepository.save(user);
			model.addAttribute("loginMessage", "SIGNED IN");
		}
		else model.addAttribute("loginMessage", "RETRY");
		
		return "login/login";
	}
}
