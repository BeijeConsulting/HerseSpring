package it.beije.herse.my.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.bean.OrderItems;
import it.beije.herse.my.repository.MyOrderItemsRepository;

@Service
public class MyOrderItemsService {
	
	@Autowired
	private MyOrderItemsRepository myOrderItemsRepository;

	public OrderItems findById(Integer id) {
		Optional<OrderItems> oi = myOrderItemsRepository.findById(id);
		
		return  oi.isPresent() ? oi.get() : null;	
	}

	public void save(OrderItems orderItem) {
			myOrderItemsRepository.save(orderItem);
	}

	public OrderItems updateOrder(OrderItems oi, OrderItems orderItem) {
		BeanUtils.copyProperties(orderItem, oi, new String[] { "id" });
		return oi;		
	}

	public boolean deleteOrder(OrderItems orderItems) {
		myOrderItemsRepository.delete(orderItems);
		return true;
	}
	
	
}
