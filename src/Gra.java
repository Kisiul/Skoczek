import java.awt.EventQueue;

public class Gra {

	public static void main(String[] args) {
// tworze panel gry, ktory tworzy plansze, ktoraladuje plik konf i rysuje prostokaty
		//docelowo panelgry trzeba uzupelnic o przyciski pauzy itp
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Panel_Gry();
			}
		});

	}

}
