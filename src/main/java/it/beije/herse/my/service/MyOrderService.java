package it.beije.herse.my.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.bean.Orders;
import it.beije.herse.my.repository.MyOrdersRepository;

@Service
public class MyOrderService {

	@Autowired
	private MyOrdersRepository myOrdersRepository;

	public List<Orders> findByUserId(Integer id) {
		List<Orders> orders = myOrdersRepository.findByUserId(id);
		return orders;
	}
}
