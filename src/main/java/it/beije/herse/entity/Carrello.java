package it.beije.herse.entity;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import it.beije.herse.service.ProductService;


public class Carrello {

	@Autowired
	private ProductService productService;
	
	private HashMap<Integer,Integer> prodotti = new HashMap<Integer,Integer>();
	private Double amount = new Double(0);
	

	public HashMap<Integer, Integer> getProdotti() {
		
		return prodotti;
		
	}


	public void addProduct(Integer p, Integer i) {

		Integer isPresent = prodotti.putIfAbsent(p, i);
	      if(isPresent!=null) {
	    	  prodotti.remove(p);
	    	  prodotti.put(p, isPresent + i);
	      }
	      
	}

	public void removeProduct(Integer p) {

		this.prodotti.remove(p);

	}
	
	public void editProduct(Integer p, Integer i, double price) {
		
		int precedente = this.prodotti.get(p);
		this.setAmount(this.getAmount() - (precedente * price));
		
		int newQ = precedente + i ;
		this.prodotti.replace(p, precedente, newQ);
		
		this.setAmount(this.getAmount() + (newQ * price));
		

	}

	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Carrello [prodotti=" + prodotti.toString() + "]";
	}
	
	
	
}
