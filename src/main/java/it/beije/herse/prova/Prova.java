package it.beije.herse.prova;

public class Prova {

	static int[][] game = new int[6][6];
	
	      public static void main(String[] args) {
	         game[3][3] = 6;
	         Object[] obj = game;
	         int[] a = {2,3, 1, 4};
	         obj[3] = a;
	         System.out.println(game[3][3]);
	     }
}
