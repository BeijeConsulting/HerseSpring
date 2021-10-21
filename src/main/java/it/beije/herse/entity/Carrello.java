package it.beije.herse.entity;

import java.util.HashMap;

public class Carrello {

	private HashMap<Product,Integer> prodotti = new HashMap();
	

	public HashMap<Product, Integer> getProdotti() {
		
		return prodotti;
		
	}


	public void addProduct(Product p, Integer i) {

		if (this.prodotti!=null && this.prodotti.containsKey(p)) {

			int precedente = prodotti.get(p);

			this.prodotti.replace(p, precedente,precedente + i);

		} else {
			this.prodotti.put(p, i);
		}
	}

	public void removeProduct(Product p) {

		this.prodotti.remove(p);

	}
	
	public void editProduct(Product p, Integer i) {
		
		int precedente = this.prodotti.get(p);

		this.prodotti.replace(p, precedente,i);

	}


	@Override
	public String toString() {
		return "Carrello [prodotti=" + prodotti + "]";
	}
	
	
	
}
