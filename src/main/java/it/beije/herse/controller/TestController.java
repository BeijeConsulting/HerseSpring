package it.beije.herse.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TestController {

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, Model model) {
		
		LocalDateTime localDateTime = LocalDateTime.now();
		
		String name = request.getParameter("name");
		
		if (name == null) {
			name = "Sconosciuto";
		}
		
		model.addAttribute("name", name);
		model.addAttribute("localDateTime", localDateTime);
		
		return "test";
	}
	
}
