package it.beije.herse.my.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.bean.CarrelloNew;
import it.beije.herse.bean.Products;
import it.beije.herse.bean.Users;
import it.beije.herse.bean.OrderItems;
import it.beije.herse.my.repository.MyProductsRepository;

@Controller
public class MyProductsController {

	@Autowired
	private MyProductsRepository myProductsRepository;

	@RequestMapping(path = "/shopProdotti", method = RequestMethod.GET)
	public String printProdotti() {
		System.out.println("Prodotti GET");
		return "myview/myprodotti";
	}

	@RequestMapping(path = "/addCarrello", method = RequestMethod.POST)
	public String addCarrello(Model model, @RequestParam Map<String, String> allParams, HttpSession session) {
		System.out.println("Prodotti POST");
		List<Products> products = myProductsRepository.findAll();
		CarrelloNew carrello = (CarrelloNew) session.getAttribute("carrello");
		if (carrello != null) {
			System.out.println("carrello diverso da null");
			for (Products p : products) {
				if (!allParams.isEmpty()) {
					System.out.println("lista non vuota");
					String quantityStr = allParams.get("" + p.getId());
					if (quantityStr != null && !quantityStr.equalsIgnoreCase("0")) {
						System.out.println("quantita diversa da null e diversa da 0");

						Integer quantity = Integer.parseInt(quantityStr);
						OrderItems orderItems = new OrderItems();

						orderItems.setId(null);
						orderItems.setOrderId(null);
						orderItems.setProductId(p.getId());
						orderItems.setQuantity(quantity);
						double sellPrice = p.getPrice() * quantity;
						orderItems.setSellPrice((int) sellPrice);

						System.out.println("OrderItem : " + orderItems);

						int index = 0;
						List<OrderItems> oiList = carrello.getOrderItems();
						if (oiList.size() == 0) {
							oiList.add(orderItems);
						} else {
							do {
								OrderItems oi = oiList.get(index);
								if (oi.getProductId() == orderItems.getProductId()) {
									// sono lo stesso prodotto quindi devo solo aggiornare la quantita e sell price
									int newQuantity = oi.getQuantity() + quantity;
									double newPrice;
									// modifico il valore dell'order item gia presente nel carrello
									if (newQuantity < p.getQuantity() && newQuantity < 10) {
										newPrice = p.getPrice() * newQuantity;
										oi.setQuantity(newQuantity);
										oi.setSellPrice((int) newPrice);
									} else if (newQuantity < p.getQuantity()) {
										newQuantity = 10;
										newPrice = p.getPrice() * newQuantity;
										oi.setQuantity(newQuantity);
										oi.setSellPrice((int) newPrice);
									} else {
										newQuantity = p.getQuantity();
										newPrice = p.getPrice() * newQuantity;
										oi.setQuantity(newQuantity);
										oi.setSellPrice((int) newPrice);
									}
								} else {
									// se il prodotto non e presente lo aggiungo direttamente al carrello
									System.out.println("nuovo order item");

									carrello.addOrderItems(orderItems);
								}
								++index;
							} while (index < carrello.getOrderItems().size());
						}
					}
				}
			}
			model.addAttribute("carrelloError", "");
			return "myview/mycarrello";
		} else

		{
			String carrelloError = "Prima di poter aggiungere articoli al carrello effettua il log in";
			model.addAttribute("carrelloError", carrelloError);
			return "myview/myprodotti";
		}
	}

	@RequestMapping(path = "/confirmation", method = RequestMethod.POST)
	public String confirmation() {
		return "myconfirmation";
	}
}
