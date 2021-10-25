package it.beije.herse.my.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
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

	public Orders findById(Integer id) {
		Optional<Orders> order = myOrdersRepository.findById(id);

		return order.isPresent() ? order.get() : null;
	}

	public void save(Orders order) {
		if (order.getDateTimeAsString() == null && order.getDateTime() == null) {
			order.setDateTime(LocalDateTime.now());
			order.setDateTime(LocalDateTime.now().toString());
		}
		myOrdersRepository.save(order);
	}

	public Orders updateOrder(Orders order, Orders updateOrder) {
		BeanUtils.copyProperties(updateOrder, order, new String[] { "id" });
		return order;
	}

	public boolean deleteOrder(Orders orders) {
		myOrdersRepository.delete(orders);
		return true;
	}

	public List<Orders> findAll() {
		List<Orders> orders = myOrdersRepository.findAll();
		return orders;
	}

}
