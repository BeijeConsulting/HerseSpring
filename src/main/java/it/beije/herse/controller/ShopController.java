package it.beije.herse.controller;

import static it.beije.herse.entity.MyShop.getProducts;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.beije.herse.entity.ManagerCRUD;
import it.beije.herse.entity.Product;

@Controller
public class ShopController {
	
	@RequestMapping(path = "/showProduct")
	public String showProduct(HttpSession session) {
		
		StringBuilder htmlEl = new StringBuilder();
		String errorQta = (String) session.getAttribute("errorQta");
		String confirm = (String) session.getAttribute("orderConfirm");
		
		if (session.getAttribute("user") == null) {
			return "";
		} else if (confirm != null && !confirm.isEmpty()) {
			confirm = "<p style=\"color:green\">" + confirm + "</p>";
			session.setAttribute("orderConfirm",confirm);
		} else if (errorQta != null && !errorQta.isEmpty()) {
			errorQta = "<p style=\"color:red\">" + errorQta + "</p>";
			session.setAttribute("errorQta", errorQta);
		}
			
		ManagerCRUD m = (ManagerCRUD)session.getAttribute("managerCRUD");
		List<Product> products = getProducts(m);

		for (Product p : products)
			htmlEl.append(newHTMLProd(p));
		
		session.setAttribute("htmlEl", htmlEl.toString());
		
		return "shop";
		
	}
	
	public String newHTMLProd(Product p) {
		
		return "<ul class=\"myli\">\r\n"
				+ "		<li class=\"myli\">" + p.getName().toUpperCase() + "</li>\r\n"
				+ "		<li class=\"myli\">" + p.getDescription().toUpperCase() + "</li>\r\n"
				+ "		<li class=\"myli\">" + p.getPrice() + "</li>\r\n"
				+ "		<li class=\"myli-float\"><input name=\"" + p.getId() + "\" type=\"number\" placeholder=\"0\"></li>\r\n"
				+ "	</ul>";
		/*"<input type=\"checkbox\" name=\"" + p.getId() + "\" value=\"" + p.getId() + "\">"
				+ "<input type=\"text\" name=\"prodName" + p.getId() + "\" value=\"" + p.getName() + "\" readonly>\n"
				+ "<input type=\"text\" name=\"prodDescription" + p.getId() + "\" value=\"" + p.getDescription()
				+ "\" readonly>\n" + "<input style=\"width:30px\" type=\"number\" name=\"prodQta" + p.getId()
				+ "\" placeholder=\"0\"><br>\n";*/
	}

}
