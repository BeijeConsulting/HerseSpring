package it.beije.herse.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;


@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/user/login")
	public String loginUser() {
		System.out.println("sono in login get");
		return "/user/loginUser";
	}
	
	@PostMapping(path = "/user/login")
	//con request param ottengo direttmente da spring i parametri
	public String authUser(HttpSession session, Model model, @RequestParam String email, @RequestParam String password) {
		System.out.println("sono in autenticazione post");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HerseSpring");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		User user = new User();

		try {
			String userSelect = "SELECT u FROM User AS u WHERE email = '"+email+"' AND password = '"+password+"'";
			Query query = entityManager.createQuery(userSelect);
			user = (User) query.getSingleResult();
			
			model.addAttribute("user", user);
			session.setAttribute("user", user);
			return "/shop/menu";
			
		} catch (PersistenceException e) {
			model.addAttribute("error", "Credenziali Errate");
			return "/user/loginUser";
		} finally {
			entityManager.close();
		}
	}
	
	@RequestMapping(path = "/user/register", method = RequestMethod.GET)
	public String getRegisterPage() {
		System.out.println("sono in register get");
		return "/user/register";
	}
	
	@RequestMapping(path = "/user/register", method = RequestMethod.POST)
	public String register(Model model, @Validated User user) {
		System.out.println("sono in register post");
		System.out.println("insert user : " + user);
		userRepository.save(user);
		model.addAttribute("message", "User " + user.getEmail() + " added");
		
		return "/user/loginUser"; // /WEB-INF/views/ + user/insert_user + .jsp
	}
	
	@GetMapping(path = "/user/users")
	public String getListUsers(Model model, @RequestParam(required = false) String email) {
		System.out.println("sono in list users get");
		List<User> listUsers;
		
		if(email != null && email.length() >0) {
			listUsers = userRepository.findByEmail(email);
		} else {
			listUsers = userRepository.findAll();
		}
		model.addAttribute("listUsers", listUsers);
		
		return "user/list_users";
	}
}
