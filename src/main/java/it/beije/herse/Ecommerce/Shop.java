package it.beije.herse.Ecommerce;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	
	public void insertUser(User user) {
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		manager.persist(user);
		transaction.commit();
	}
	
	public List<Product> findProducts(){
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		
		Root<Product> productRoot = criteriaQuery.from(Product.class);
		
		CriteriaQuery<Product> select = criteriaQuery.select(productRoot);
		TypedQuery<Product> query = manager.createQuery(select);
		
		return query.getResultList();
	}

	public List<Product> findProductsById(int productId) {
		String select = "SELECT p FROM Product as p WHERE id=" + productId;
		Query query = manager.createQuery(select);
		List<Product> products = query.getResultList();
		
		return products;
	}
	
	public boolean productExists(int productId) {
		Product product = manager.find(Product.class, productId);
		if(product != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean checkQuantity(int productId, int quantity, int userId) {
		Product product = manager.find(Product.class, productId);

		if(product.getQuantity() < quantity) {
			System.out.println("Quantità maggiore rispetto a quella disponibile");
			return false;
		} else {

			System.out.println(product);

			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			product.setQuantity(product.getQuantity()-quantity); //settatto la quantità nuova di product
			transaction.commit();

			return true;
		}
	}
	
	public void addCart(HashMap<Integer, Object> map) {
		
	}

}
