package it.beije.herse.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.beije.herse.bean.OrderItems;
import it.beije.herse.bean.Products;
import it.beije.herse.bean.SingletonEntityManagerFactory;
import it.beije.herse.bean.Users;

public class ProductsModel {
	private EntityManager entityManager;
	private String queryRequest;
	
	public ProductsModel() {
		this.entityManager = SingletonEntityManagerFactory.newEntityManager();
	}
	
	public List<Products> getProducts() {
		this.queryRequest = "SELECT x FROM Products as x";
		Query query = entityManager.createQuery(queryRequest);
		List<Products> result = query.getResultList();
		return result;
	}
	
	public StringBuilder printProducts(Products products) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("NAME: ").append(products.getName()).append(" | ");
		stringBuilder.append("DESCRIZIONE: ").append(products.getDescription()).append(" | ");
		stringBuilder.append("PREZZO: ").append(products.getPrice()).append(" | ");
		stringBuilder.append("QUANTITA': ").append(products.getQuantity());
		

		return stringBuilder;
	}
	
	public int getProductId(Products products) {
		return products.getId();
	}
	
	public String getImageFromOrderItems(OrderItems orderItems) {
		List<Products> products = getProducts();
		
		for(Products p : products) {
			if(p.getId() == orderItems.getProductId()) {
				return p.getImagePath();
			}
		}
		return null;
	}
}
