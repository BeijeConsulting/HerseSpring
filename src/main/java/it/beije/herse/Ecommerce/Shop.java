package it.beije.herse.Ecommerce;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class Shop {
	
	final static EntityManager manager = ShopEntityManager.newEntityManager();

	public int checkCredential(String email, String password) {
		String select ="SELECT u FROM User as u";
		Query query = manager.createQuery(select);
		List<User> user = query.getResultList();

		for(User u: user) {
			if(u.getPassword().equals(password) && u.getEmail().equalsIgnoreCase(email)) {
				return u.getId();
			}
		}
		return 0;
	}
	
	public List<Product> findProducts(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		
		CriteriaQuery<Product> select = criteriaQuery.select(productRoot);
		TypedQuery<Product> query = manager.createQuery(select);
		
		return query.getResultList();
	}
}
