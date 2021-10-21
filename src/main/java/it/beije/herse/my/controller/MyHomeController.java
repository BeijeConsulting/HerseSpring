package it.beije.herse.my.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import it.beije.herse.my.repository.MyOrdersRepository;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import it.beije.herse.bean.*;
import it.beije.herse.model.ProductsModel;
import it.beije.herse.my.repository.MyOrderItemsRepository;
import it.beije.herse.my.repository.MyProductsRepository;

@Controller
public class MyHomeController {

	@Autowired
	private MyProductsRepository myProductsRepository;
	private MyOrderItemsRepository MyOrderItems;
	private MyOrdersRepository myOrders;

	

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String prodottiGet() {
		System.out.println("sono in home GET home");
		return "myview/myhome";
	}

	@RequestMapping(path = "/goProdotti", method = RequestMethod.POST)
	public String prodotti(HttpSession session) {
		System.out.println("sono in home POST products");
		List<Products> products = myProductsRepository.findAll();
		session.setAttribute("products", products);
		return "myview/myprodotti";
	}

	@RequestMapping(path = "/goProdotti", method = RequestMethod.GET)
	public String goProdotti() {
		System.out.println("sono in home GET products");
		return "myview/myprodotti";
	}
	
//	@RequestMapping(path = "orders1" ,method = RequestMethod.POST)
//	public String order(Model model, HttpSession session) {
//		Users user = (Users) session.getAttribute("authUser");
//		List<Orders> orders = myOrders.findByUserId(user.getId());
//		List<OrderItems> orderItems = new ArrayList<OrderItems>();
//		Map<Integer, List> orderEorderItem = new HashMap();
//		
//		for (Orders o : orders) {
//			Integer oId = o.getId();
//			orderItems = MyOrderItems.findByOrderId(o.getId());
//			orderEorderItem.put(oId, orderItems);
//		}
//		
//		model.addAttribute("mappa",orderEorderItem);
//		return "myview/myorders";
//	}
}
