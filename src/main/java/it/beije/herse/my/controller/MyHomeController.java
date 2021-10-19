package it.beije.herse.my.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.beije.herse.bean.Products;
import it.beije.herse.model.ProductsModel;

@Controller
public class MyHomeController {

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String prodottiGet() {
		System.out.println("sono in prodotti GET");
		return "myview/myhome";
	}

	@RequestMapping(path = "/prodotti", method = RequestMethod.POST)
	public String prodotti(Model model) {
		System.out.println("sono in prodotti POST");
		StringBuilder stringBuilder = new StringBuilder();
		ProductsModel productsModel = new ProductsModel();
		List<Products> products = productsModel.getProducts();
		
		for (Products p : products) {
			stringBuilder.append("Name: ").append(p.getName()).append(" | ");
			stringBuilder.append("Description: ").append(p.getDescription()).append(" | ");
			stringBuilder.append("Price: ").append(p.getPrice()).append(" | ");
			stringBuilder.append("Quantity: ").append(p.getQuantity()).append("<br>");
		}
		
		model.addAttribute("products", products);
		return "myview/prodotti";
	}
}
