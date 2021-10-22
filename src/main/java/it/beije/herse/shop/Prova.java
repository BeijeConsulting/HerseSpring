package it.beije.herse.shop;

public class Prova {
	final int X = 100;
	final void ciao() {
		System.out.println("persona");
	}
	static int x = 11;
	
	
	public static void main(String[] args) {
		System.out.println(new Figlia().x);

	}

}
	class Figlia extends Prova{
		int x = 12;

	}

