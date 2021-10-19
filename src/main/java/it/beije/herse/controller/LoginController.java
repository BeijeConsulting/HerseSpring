package it.beije.herse.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

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
	//con request param ottengo direttmente da spring i parametri
	public String authUser(HttpSession session, Model model, @RequestParam String email, @RequestParam String password) {
		System.out.println("sono in autenticazione post");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-shop");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		User user = new User();

		try {
			String userSelect = "SELECT u FROM User AS u WHERE email = '"+email+"' AND password = "+password;
			Query query = entityManager.createQuery(userSelect);
			user = (User) query.getSingleResult();
			
			model.addAttribute("user", user);
			session.setAttribute("user", user);
			return "menu";
			
		} catch (PersistenceException e) {
			model.addAttribute("error", "Credenziali Errate");
			return "loginUser";
		} finally {
			entityManager.close();
		}
	}
}
