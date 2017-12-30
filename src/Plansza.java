import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


/**
*	Plansza.
*
*	Zawiera panel rozgrywki.
*	Wczytuje ustawienia bezposrednio z parsowanego pliku conf.properties.
*/

public class Plansza extends JPanel implements KeyListener {
	Status stan_gry;
	public Parsowanie pars;
	//private int x;
	//public Skoczek skoczek;

/**
*	Konstruktor.
*
*/
	public Plansza(Status s){
		stan_gry = s;
		
		//laduje konfiguracje
		pars = new Parsowanie();
		pars.loadProperties();
		// rozmiar planszy odczytany z pliku
		setPreferredSize(new Dimension(pars.parsuj("rozmiar_planszy_x"), pars.parsuj("rozmiar_planszy_y")));
		// Utworzenie skoczka i jego pozycja ustawiana z pliku
		//skoczek = new Skoczek();
		//skoczek.setPozycja_skoczka_x(pars.parsuj("pierwotna_pozycja_skoczka_x"));
		//skoczek.setPozycja_skoczka_y(pars.parsuj("pierwotna_pozycja_skoczka_y"));
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// rysuje wszystkie przeszkody, zaleznie od parametru konfiguracyjnego z pliku: liczba przeszkod. Grubosc przeszkody ustalona arbitralnie na 12 nie wiadomo czemu
		// docelowo parsowanie w logice i zmieniac wartosc wstatusie, a tu pobierac sie powinno ze statusu, ale to pozniej
		for (int i=0; i<pars.parsuj("liczba_przeszkod"); i++)
		{
			g2d.fillRect(pars.parsuj("x"+(i+1)), pars.parsuj("y"+(i+1)), pars.parsuj("dl"+(i+1)), 12 );
		}
		g2d.setColor(Color.RED);
		g2d.fillRect(stan_gry.wez_pozycje_x(), stan_gry.wez_pozycje_y(), 30, 30);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case 37:
			System.out.println("alewo");
			break;
		case 38:
			System.out.println("agora");
			break;
		case 39:
			System.out.println("aprawo");
			break;
		case 40:
			System.out.println("adol");
			break;
		default:
			System.out.println("aasdf");
			break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




}
