package it.beije.herse.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.*;
import it.beije.herse.repository.*;
import it.beije.herse.service.ProductService;

@Controller
public class EcommerceController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	ProductService productService;

//	@GetMapping(path= "/shop/catalogo")
//	public String catalogo()  {
//		System.out.println("sono in catalogo get");
//		return "/shop/catalogo";
//	}

	@GetMapping(path = "/shop/catalogo")
	public String catalogo(Model model, @RequestParam(required = false) Integer id) {
		System.out.println("sono in list products get");
		List<Product> listProducts = productRepository.findAll();
		model.addAttribute("listProducts", listProducts);

		return "shop/catalogo";
	}

	@GetMapping(path = "/shop/menu")
	public String menu() {
		System.out.println("sono in menu get");
		return "/shop/menu";
	}

	// se salvo qualcosa nel model non lo posso riprendere in altre jsp, il model
	// attribute si elimina al di fuori di quella jsp
	@GetMapping(path = "/shop/ordine")
	public String ordine(HttpSession session) {
		System.out.println("sono in ordine get");
		List<Product> listProducts = productRepository.findAll();
		session.setAttribute("listProducts", listProducts);
		return "/shop/ordine";
	}

	@PostMapping(path = "/shop/ordine")
	public String getCarrello(HttpSession session, Model model, @RequestParam(required = false) String id,
			@RequestParam(required = false) String quantita) {
		System.out.println("sono in carrello post");
		Carrello carrelloContenitore = new Carrello();
		HashMap<Product, Integer> cart = new HashMap<>();
		
		String[] idProd = id.split(",");
		String[] quantitaProd = quantita.split(",");
		
		if (idProd != null && idProd.length == quantitaProd.length) {
			
			for (int i = 0; i < idProd.length; i++) {
				Product resultProduct = productService.findById(Integer.valueOf(idProd[i].trim()));
				if (resultProduct != null) {
					if (resultProduct.getQuantity() >= Integer.valueOf(quantitaProd[i].trim())) {
						cart.put(resultProduct, Integer.valueOf(quantitaProd[i].trim()));
						
					} else {
						session.setAttribute("errorQuantity", "Quantità prodotto insufficiente");
						return "/shop/ordine";
					}
				} else {
					session.setAttribute("errorIdProduct", "Prodotto non presente");
					return "/shop/ordine";
				}
			}
			carrelloContenitore.setCarrello(cart);
			session.setAttribute("carrello", carrelloContenitore);
			return "shop/carrello";
			
		} else {
			session.setAttribute("errorInput", "Inserisci id e quantità");
			return "/shop/ordine";
		}
	}
	
	@GetMapping(path="/shop/acquista")
	public String pay(HttpSession session) {
		System.out.println("sono in acquista");
		
		Carrello carrello = (Carrello)session.getAttribute("carrello");
		System.out.println("carrello : "+carrello);
		User user = (User)session.getAttribute("user");
		System.out.println("user : "+user);
		
		Double total = (Double)session.getAttribute("total");
		//aggiungi if
		try {
			Order order = new Order(user.getId(), total, LocalDateTime.now());
			orderRepository.save(order);
			
			for ( Map.Entry<Product, Integer> entry : carrello.getCarrello().entrySet()) {
			    Integer quantity = entry.getValue();
			    
			    Product differentProduct = productService.findById(entry.getKey().getId());
			    differentProduct.setQuantity(entry.getKey().getQuantity() - quantity);
			    OrderItem orderItem = new OrderItem(order.getId(), differentProduct.getId(), differentProduct.getPrice(), quantity);
			    productRepository.save(differentProduct); //modifica
			    orderItemRepository.save(orderItem); //modifica
			}
			session.removeAttribute("carrello");
			return "/shop/conferma_acquisto";
		} catch(Exception ex) {
			session.setAttribute("errorSql", "Impossibile processare l'ordine. Riprovare il pagamento");
			return "/shop/carrello";
		}
		
	}

}
