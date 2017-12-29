import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
*	Plansza.
*
*	Zawiera panel rozgrywki.
*	Wczytuje ustawienia bezposrednio z parsowanego pliku conf.properties.
*/

public class Plansza extends JPanel {
	public Parsowanie pars;

/**
*	Konstruktor.
*
*/
	public Plansza() {
		
		//laduje konfiguracje
		pars = new Parsowanie();
		pars.loadProperties(1);
		// rozmiar planszy odczytany z pliku
		setPreferredSize(new Dimension(pars.parsuj("rozmiar_planszy_x"), pars.parsuj("rozmiar_planszy_y")));
		// Utworzenie skoczka i jego pozycja ustawiana z pliku

		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// rysuje wszystkie przeszkody, zaleznie od parametru konfiguracyjnego z pliku: liczba przeszkod. Grubosc przeszkody ustalona arbitralnie na 12 nie wiadomo czemu
		for (int i=0; i<pars.parsuj("liczba_przeszkod"); i++)
		{
			g2d.fillRect(pars.parsuj("x"+(i+1)), pars.parsuj("y"+(i+1)), pars.parsuj("dl"+(i+1)), 12 );
		}
		
		//rysuje skoczka
		g2d.setColor(Color.GRAY);
		g2d.fillRect(Status.pozycja_gracza[0], Status.pozycja_gracza[1] , 30, 30);
		//rysuje cel
		g2d.setColor(Color.blue);
		g2d.fillRect(pars.parsuj("cel_skoczka_x"),  pars.parsuj("cel_skoczka_y") , 30, 30);
		
	}
	public void pisz()
	{
		System.out.println("skldfbls");
	}

}
