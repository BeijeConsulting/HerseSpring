package it.beije.herse.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.beije.herse.shop.entity.ShopOrder;
import it.beije.herse.shop.entity.ShopOrderItem;
import it.beije.herse.shop.entity.ShopProduct;
import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.repository.ShopOrderItemRepository;
import it.beije.herse.shop.repository.ShopOrderRepository;
import it.beije.herse.shop.repository.ShopProductRepository;
import it.beije.herse.shop.repository.ShopUserRepository;

@Service
public class UserMenuService {
	
	@Autowired
	private ShopUserRepository userRepository;
	
	@Autowired
	private ShopOrderRepository orderRepository;
	
	@Autowired
	private ShopOrderItemRepository orderItemRepository;
	
	@Autowired
	private ShopProductRepository productRepository;
	
	// READ USER (find by id)
	public ShopUser findUserById(Integer userId) {
		ShopUser user = null;
		Optional<ShopUser> u = userRepository.findById(userId);
		if(u!=null & u.isPresent()) user = u.get();
		return user;
	}
	
	// UPDATE USER
	public ShopUser updateUser(ShopUser loggedUser, ShopUser updatedUser) {
		if(updatedUser.getName()!=null && updatedUser.getName().length()>0) loggedUser.setName(updatedUser.getName());
		if(updatedUser.getSurname()!=null && updatedUser.getSurname().length()>0) loggedUser.setSurname(updatedUser.getSurname());
		if(updatedUser.getEmail()!=null && updatedUser.getEmail().length()>0) loggedUser.setEmail(updatedUser.getEmail());
		if(updatedUser.getPassword()!=null && updatedUser.getPassword().length()>0) loggedUser.setPassword(updatedUser.getPassword());
		
		return userRepository.save(loggedUser);
	}

	// DELETE USER
	public ShopUser deleteUser(ShopUser loggedUser) {
		if(loggedUser!=null) userRepository.delete(loggedUser);
		return loggedUser;
	}
	
	// READ ORDER (find by userId)
	public Map<ShopOrder, List<ShopOrderItem>> getOrderHistory(ShopUser loggedUser) {
		Integer userId = loggedUser.getId();
		
		List<ShopOrder> orders = orderRepository.findByUserId(userId);
		Map<ShopOrder, List<ShopOrderItem>> orderHistory = new HashMap<ShopOrder, List<ShopOrderItem>>();
		for(ShopOrder o : orders) {
			Integer orderId = o.getId();
			List<ShopOrderItem> items = orderItemRepository.findByOrderId(orderId);
			orderHistory.put(o, items);
		}
		
		return orderHistory;
	}
	
	public List<ShopOrder> getOrderHistory(Integer userId) {
		return orderRepository.findByUserId(userId);
	}
	
	// CREATE PRODUCT
	public ShopProduct sellProduct(ShopProduct userProduct) {
		if(userProduct!=null) return productRepository.save(userProduct);
		else return null;
	}
	
	// UPDATE PRODUCT
	public ShopProduct updateProduct(Integer productId, ShopProduct updates) {
		ShopProduct userProduct = null;
		
		Optional<ShopProduct> p = productRepository.findById(productId);
		if(p!=null && p.isPresent()) userProduct = p.get();
		else return null;
		
		if(updates.getName()!=null && updates.getName().length()>0) userProduct.setName(updates.getName());
		if(updates.getDescription()!=null && updates.getDescription().length()>0) userProduct.setDescription(updates.getDescription());
		if(updates.getPrice()!=null && updates.getPrice()>0) userProduct.setPrice(updates.getPrice());
		if(updates.getQuantity()!=null && updates.getQuantity()>0) userProduct.setQuantity(updates.getQuantity());
		
		return productRepository.save(userProduct);
	}
	
	// DELETE PRODUCT
	public ShopProduct deleteProduct(Integer productId) {
		ShopProduct userProduct = null;
		
		Optional<ShopProduct> p = productRepository.findById(productId);
		if(p!=null && p.isPresent()) {
			userProduct = p.get();
			productRepository.delete(userProduct);
		}
		
		return userProduct;
	}
}
