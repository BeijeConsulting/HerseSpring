package it.beije.herse.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.herse.entity.Item;
import it.beije.herse.entity.Order;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.repository.ProductRepository;

@Controller
public class ShopController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(path = "/showProduct")
	public String showProduct(HttpSession session, Model model) {

		String redirect = "viewproduct";

		if(session.getAttribute("user") != null) {
			List<Product> products = productRepository.findAll();
			model.addAttribute("products", products);
		} else {
			redirect = "login";
		}

		return redirect;

	}

	@RequestMapping(path = "/carrello", method = RequestMethod.POST)
	public String riepilogo(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		String redirect = "riepilogo";

		List<Product> products = productRepository.findAll();

		List<Item> items = new ArrayList<>();

		for(Product p : products) {

			String qta = request.getParameter(p.getId().toString());

			if(validateQta(qta,p)) {
				Item item = new Item();
				item.setUserId(((User)session.getAttribute("user")).getId());
				item.setProductId(p.getId());
				item.setProductName(p.getName());
				item.setProductDescription(p.getDescription());
				item.setAmount(item.getAmount() + (p.getPrice() * Double.valueOf(qta)));
				item.setQuantity(Integer.valueOf(qta));
				items.add(item);

			}

		}
		
		if(items.size() > 0) {
			model.addAttribute("items", items);
			session.setAttribute("items", items);
		} else {
			redirect = "home";
		}

		return redirect;

	}

	@RequestMapping(path = "/carrello", method = RequestMethod.GET)
	public String riepilogo(HttpSession session, Model model) {

		String redirect = "riepilogo";

		if(session.getAttribute("items") != null && !((List<Item>)session.getAttribute("items")).isEmpty())
			model.addAttribute("items", (List<Item>)session.getAttribute("items"));
		else {
			model.addAttribute("voidCart", "Carrello vuoto");
			redirect = "home";
		}

		return redirect;
	}
	
	@RequestMapping(path = "/confirmOrder", method = RequestMethod.POST)
	public String confirmOrder(HttpSession session, Model model) {

		String redirect = "home";

		if(session.getAttribute("items") != null && !((List<Item>)session.getAttribute("items")).isEmpty()) {
			List<Item> items = (List<Item>)session.getAttribute("items");
			List<OrderItem> it = new ArrayList<>();
			
			Order order = new Order();
			order.setUserId(items.get(0).getUserId());
			order.setDateTime(LocalDateTime.now());
			
			for(Item i : items) {
				OrderItem item = new OrderItem();
				item.setProductId(i.getProductId());
				item.setQuantity(i.getQuantity());
				item.setSellPrice(i.getAmount() / Double.parseDouble(i.getQuantity().toString()));
				item.setOrderId(order.getId());
				it.add(item);
			}
			
		} else {
			model.addAttribute("voidCart", "Carrello vuoto");
			redirect = "home";
		}

		return redirect;
	}

	@RequestMapping(path = "/deleteItems")
	public String deleteItems(HttpSession session) {

		session.removeAttribute("items");

		return "home";

	}

	public static boolean validateQta(String qta, Product p) {

		boolean validate = false;

		if(qta != null && !qta.isEmpty())
			validate = (Double.valueOf(qta) > 0 && Double.valueOf(qta) <= p.getQuantity());

		return validate;

	}

	public static Double calcAmountOrder(List<OrderItem> items) {

		Double amount = 0.0;

		for(OrderItem item : items)
			amount += item.getSellPrice();

		return amount;

	}

}