package it.beije.herse.Ecommerce;

public class Carrello {
	
	private int quantity;

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Carrello [quantity=" + quantity + "]";
	}

}
