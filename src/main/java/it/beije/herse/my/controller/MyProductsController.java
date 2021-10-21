package it.beije.herse.my.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import it.beije.herse.bean.*;
import it.beije.herse.my.repository.MyProductsRepository;
import it.beije.herse.JpaEntityManager;

@Controller
public class MyProductsController {

	
	@Autowired
	private MyProductsRepository myProductsRepository;

	@RequestMapping(path = "/shopProdotti", method = RequestMethod.GET)
	public String printProdotti() {
		System.out.println("Prodotti GET");
		return "myview/myprodotti";
	}
	
	@RequestMapping(path = "/goCarrello", method = RequestMethod.POST)
	public String goCarrello() {
		System.out.println("Prodotti POST");
		return "myview/mycarrello";
	}

	@RequestMapping(path = "/addCarrello", method = RequestMethod.POST)
	public String addCarrello(Model model, @RequestParam Map<String, String> allParams, HttpSession session) {
		System.out.println("Prodotti POST");
		List<Products> products = myProductsRepository.findAll();
		CarrelloNew carrello = (CarrelloNew) session.getAttribute("carrello");
		if (carrello != null) {
//			System.out.println("carrello diverso da null");
			for (Products p : products) {
				if (!allParams.isEmpty()) {
//					System.out.println("lista non vuota");
					String quantityStr = allParams.get("" + p.getId());
					if (quantityStr != null && !quantityStr.equalsIgnoreCase("0")) {

						Integer quantity = Integer.parseInt(quantityStr);
//						System.out.println("quantita :" + quantity );
						OrderItems orderItems = new OrderItems();

						orderItems.setId(null);
						orderItems.setOrderId(null);
						orderItems.setProductId(p.getId());
						orderItems.setQuantity(quantity);
						double sellPrice = p.getPrice() * quantity;
						orderItems.setSellPrice((int) sellPrice);

//						System.out.println("OrderItem : " + orderItems);

						List<OrderItems> oiList = carrello.getOrderItems();
						if (oiList.size() == 0) {
//							System.out.println("istanzio il primo elemento del carrello");
							oiList.add(orderItems);
						} else {
							boolean found = false;
							for (OrderItems oi : oiList) {
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
									found = true;
									break;
								}
							}
							if (!found) {
								// se il prodotto non e presente lo aggiungo direttamente al carrello
//							System.out.println("nuovo order item");
								carrello.addOrderItems(orderItems);
							}
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

	@RequestMapping(path = "/aggiornaCarrello", method = RequestMethod.POST)
	public String aggiornaCarrello(Model model, @RequestParam Map<String, String> allParams, HttpSession session) {

//		List<Products> products = myProductsRepository.findAll();
		CarrelloNew carrello = (CarrelloNew) session.getAttribute("carrello");
		if (carrello != null) {
//			System.out.println("carrello diverso da null");
			List<OrderItems> oiList = carrello.getOrderItems();

			boolean exit = false;
			do {
				if (oiList.size() > 0) {
					for (OrderItems oi : oiList) {
						exit = false;
						if (!allParams.isEmpty()) {
//					System.out.println("lista non vuota");
							String quantityStr = allParams.get("" + oi.getProductId());
							if (quantityStr != null) {
								Integer quantity = Integer.parseInt(quantityStr);
								if (quantity != 0 && quantity != oi.getQuantity()) {
									double productSellPrice = oi.getSellPrice() / oi.getQuantity();
									double newSellPrice = productSellPrice * quantity;

									oi.setQuantity(quantity);
									oi.setSellPrice((int) newSellPrice);
									exit = false;
								} else if (quantity == 0) {
									oiList.remove(oi);
									exit = true;
									break;
								}
							}
						}
					}
				}else {
					exit = false;
				}
			} while (exit);

		}
		return "myview/mycarrello";
	}

	@RequestMapping(path = "/confirmation", method = RequestMethod.POST)
	public String confirmation(HttpSession session,Model model) {
		///////
		
		if (session.getAttribute("authUser") != null) {
			if (session.getAttribute("carrello") != null) {
				CarrelloNew carrelloNew = (CarrelloNew) session.getAttribute("carrello");
				List<OrderItems> orderItems = carrelloNew.getOrderItems();
//				OrderModel orderModel = new OrderModel();
				if (orderItems.size() > 0) {
					// posso piazzare l'ordine perche il carrello non e vuoto
					Users user = (Users) session.getAttribute("authUser");
					int quantityOrder = 0;

					for (OrderItems oi : orderItems) {
						quantityOrder += oi.getQuantity();
					}

					int orderId = placeOrder(user, quantityOrder);
					// pizzo poi gli order items e aggiorno le quantita nel db
					for (OrderItems oi : orderItems) {
						placeOrderItems(oi, orderId, session);
					}
					CarrelloNew carrelloNew2 = new CarrelloNew(model);
					session.setAttribute("carrello", carrelloNew2);
					model.addAttribute("errorCarEmpty", "");
					model.addAttribute("errorCar", "");
					model.addAttribute("errorAuth", "");

					return "myview/myconfirmation";
				} else {
					String errorCarEmpty = "errore carrello";
					model.addAttribute("errorCarEmpty", errorCarEmpty);
					return "myview/mycarrello";
				}
			} else {
				// qualcosa non va con il carrello
				String errorCar = "errore carrello";
				model.addAttribute("errorCar", errorCar);
				return "myview/mycarrello";
			}
		} else {
			// utente non loggato
			String errorAuth = "errore user log";
			model.addAttribute("errorAuth", errorAuth);
			return "myview/mycarrello";
		}
		
		//////
		
	}
	
	
	public int placeOrder(Users users,int quantity) {
		EntityManager entityManager = JpaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Orders order = new Orders();
		
		order.setId(null);
		order.setDateTime(LocalDateTime.now());
		order.setUserId(users.getId());
		order.setAmount(quantity);
		
		entityManager.persist(order);
		transaction.commit();
		
		return order.getId();
	}
	
	public void placeOrderItems(OrderItems orderItems, int orderId, HttpSession session) {
//		ProductsModel productsModel = new ProductsModel();
		List<Products> products = (List<Products>)session.getAttribute("products");
	
		//qui piazzo l'orderItem
		EntityManager entityManager = JpaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		orderItems.setOrderId(orderId);

		entityManager.persist(orderItems);
		transaction.commit();
		
		//A questo punto scalo la quantita
		int itemId = orderItems.getProductId();
		double quantity = orderItems.getQuantity();
		
		for(Products p : products) {
			if(p.getId() == itemId) {
				updateAvaibleQuantity(itemId,(int)quantity);
			}
		}
	}
	
	public void updateAvaibleQuantity(int itemId, int quantity) {
		EntityManager entityManager = JpaEntityManager.getInstance().createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Products product = entityManager.find(Products.class, itemId);
		int oldQuantity = product.getQuantity();
		int newQuantity = oldQuantity - quantity;
		product.setQuantity(newQuantity);
		
		
		entityManager.persist(product);
		transaction.commit();
	}
	
}
