package it.beije.herse.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.model.User;


@Controller
public class LoginController {

	@GetMapping(path = "/loginUser")
	public String loginUser() {
		System.out.println("sono in login get");
		return "loginUser";
	}
	
	@PostMapping(path = "/loginUser")
	public String authUser(Model model, @RequestParam String email, @RequestParam String password) {
		System.out.println("sono in autenticazione post");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		String reply = "";
		User user = new User();

		try {
			String userSelect = "SELECT u FROM User AS u WHERE email = '"+email+"' AND password = "+password;
			Query query = entityManager.createQuery(userSelect);
			user = (User) query.getSingleResult();
			
			model.addAttribute("user", user);
			
			reply= "menuUser";
			
		} catch (PersistenceException e) {
			model.addAttribute("error", "Credenziali Errate");
			reply = "loginUser";
		}

		entityManager.close();
		return reply;
	}
}
