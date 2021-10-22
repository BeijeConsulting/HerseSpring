package it.beije.herse.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Carrello {

	private HashMap<Integer,Integer> prodotti = new HashMap();
	

	public HashMap<Integer, Integer> getProdotti() {
		
		return prodotti;
		
	}


	public void addProduct(Integer p, Integer i) {

		Integer isPresent = prodotti.putIfAbsent(p, i);
	      if(isPresent!=null) {
	    	  prodotti.remove(p);
	    	  prodotti.put(p, isPresent + i);
	      }
	      
	      System.out.println(isPresent);
	      
	}

	public void removeProduct(Integer p) {

		this.prodotti.remove(p);

	}
	
	public void editProduct(Integer p, Integer i) {
		
		int precedente = this.prodotti.get(p);

		this.prodotti.replace(p, precedente, precedente+i);

	}


	@Override
	public String toString() {
		return "Carrello [prodotti=" + prodotti.toString() + "]";
	}
	
	
	
}
