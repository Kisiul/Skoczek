package okna;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gra.Parsowanie;
import status.Status;


/**
*	Plansza.
*
*	Zawiera panel rozgrywki.
*	Wczytuje ustawienia bezposrednio z parsowanego pliku conf.properties.
*/

public class Plansza extends JPanel {
	Status stan_gry;
	public Parsowanie pars;
	private File f;
	private JLabel l_czas;
	long i=0;


/**
*	Konstruktor.
*
*/
	public Plansza(Status s){
		stan_gry = s;
		
		//laduje konfiguracje
		f= new File("conf.properties");
		pars = new Parsowanie();
		pars.loadProperties(f);
		// rozmiar planszy odczytany z pliku
		setPreferredSize(new Dimension(pars.parsuj("rozmiar_planszy_x"), pars.parsuj("rozmiar_planszy_y")));
		 l_czas = new JLabel("czas");
		 add(l_czas);

		
		
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
		g2d.fillRect(stan_gry.wez_pozycje_x(), stan_gry.wez_pozycje_y(), pars.parsuj("rozmiar_skoczka"), pars.parsuj("rozmiar_skoczka"));
		
		l_czas.setText(Long.toString(stan_gry.wez_czas()/1000));
	}






}
