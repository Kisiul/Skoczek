import java.awt.Color;

/**
 *Klasa zawiera dane biegajacego ludzika:
	- pozycje
	- grafike-obrazek/animacje
 */
public class Skoczek {
	private int pozycja_skoczka_x;
	private int pozycja_skoczka_y;
	Color kolor;
	
	public Skoczek() {
		kolor=Color.GRAY;
	}
	
	
	
// getters n setters	
	public int getPozycja_skoczka_x() {
		return pozycja_skoczka_x;
	}
	public void setPozycja_skoczka_x(int pozycja_skoczka_x) {
		this.pozycja_skoczka_x = pozycja_skoczka_x;
	}
	public int getPozycja_skoczka_y() {
		return pozycja_skoczka_y;
	}
	public void setPozycja_skoczka_y(int pozycja_skoczka_y) {
		this.pozycja_skoczka_y = pozycja_skoczka_y;
	}
	public Color getKolor() {
		return kolor;		
	}
	
	
	

}
