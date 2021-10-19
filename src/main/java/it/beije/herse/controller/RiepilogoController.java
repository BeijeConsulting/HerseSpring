package it.beije.herse.controller;

import static it.beije.herse.entity.MyShop.getProduct;
import static it.beije.herse.entity.MyShop.getProducts;
import static it.beije.herse.entity.MyShop.setOrderItem;
import static it.beije.herse.entity.MyShop.validateQta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.herse.entity.Carrello;
import it.beije.herse.entity.ManagerCRUD;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.Product;

@Controller
public class RiepilogoController {

	@RequestMapping(path = "/review", method = RequestMethod.POST)
	public String review(HttpServletRequest request, HttpSession session, Model model) {

		String redirect = "riepilogo";
		ManagerCRUD m = (ManagerCRUD)session.getAttribute("managerCRUD");

		if(session.getAttribute("user") == null)
			return "";
		if(session.getAttribute("htmlEl") != null)
			session.removeAttribute("htmlEl");

		Carrello c = new Carrello();
		StringBuilder error = new StringBuilder();

		List<Product> list = getProducts(m);

		for(Product p : list) {

			String qta = request.getParameter(p.getId().toString());
			System.out.println(qta + " " + list.size());

			if(qta != null && !qta.isEmpty()) {
				
				if(validateQta(qta, p)) {
					c.addItem(setOrderItem(p.getId(), Integer.valueOf(qta), p.getPrice()));
				} else {

					if(p.getQuantity() > 1)
						error.append("Sono disponibili " + p.getQuantity() + " di " + p.getName());
					else
						error.append("E' disponibile " + p.getQuantity() + " di " + p.getName());

					model.addAttribute("errorQta", error.toString());

					return "home";

				}
				
			}

		}

		session.setAttribute("carrello", c);

		StringBuilder htmlEl = new StringBuilder();

		for(OrderItem o : c.getItems())
			htmlEl.append(newHTMLProd(getProduct(o.getProductId(),m)));

		if(htmlEl.isEmpty())
			model.addAttribute("void", "Carrello vuoto");

		model.addAttribute("htmlEl", htmlEl.toString());

		return redirect;

	}

	public String newHTMLProd(Product p) {

		return "<ul class=\"myli\">\r\n"
				+ "		<li class=\"myli\">" + p.getName().toUpperCase() + "</li>\r\n"
				+ "		<li class=\"myli\">" + p.getDescription().toUpperCase() + "</li>\r\n"
				+ "		<li class=\"myli\">" + p.getPrice() + "</li>\r\n"
				+ "	</ul>";

	}

}
