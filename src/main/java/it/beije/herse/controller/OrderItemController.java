package it.beije.herse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.OrderItem;
import it.beije.herse.service.OrderItemService;

@Controller
public class OrderItemController {

	@Autowired
	OrderItemService orderItemService;
	

}
