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
	 * tablica przechowuj¹ca dane o planszy
	 * 0- œciana
	 * 1- wolna przestrzeñ
	 * 2- okno
	 */
	private int[][] plansza;
	/**
	 * romiar kafelka (bedzie zdefiniowany jako szerokosc okna/liczba kafelków)
	 */
	public int kwadracik;
	
	public int xKulka, yKulka;


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

		rysujPlansze(1, g);
		g.setColor(Color.red);
		g.fillRect(stan_gry.wez_pozycje_x(), stan_gry.wez_pozycje_y(), kwadracik, kwadracik);
		
		l_czas.setText(Long.toString(stan_gry.wez_czas()/1000));
	}
	
/** rysuje kwadraty zgodnie z plikiem konfigura, skoczek rysowany jest oddzielnie w paintcomponent*/	
	public void rysujPlansze(int num, Graphics g)
	{
		//Graphics2D g2d = (Graphics2D) g;
		plansza = pars.odczytZPliku(num);
		int szerokosc = pars.getszerokosc();
		int wysokosc = pars.getwysokosc();
		if (getHeight()<getWidth())
			kwadracik = getHeight()/(wysokosc+1);
		else
			kwadracik = getWidth()/(szerokosc+1);
		
		for (int j= 0; j<wysokosc; j++)
		{
			for (int i= 0; i<szerokosc; i++)
			{
				if (plansza[j][i] == 0)
				{
					g.setColor(Color.GRAY);
					g.fillRect(i*kwadracik, j*kwadracik, kwadracik, kwadracik);
				}
				if (plansza[j][i] == 2)
				{
					g.setColor(Color.blue);
					g.fillRect(i*kwadracik, j*kwadracik, kwadracik, kwadracik);
				}
				if ((plansza[j][i] == 1) ||(plansza[j][i] == 3) )
				{
					g.setColor(Color.WHITE);
					g.fillRect(i*kwadracik, j*kwadracik, kwadracik, kwadracik);
				}
				if (plansza[j][i] == 3) 
				{
					xKulka = i*kwadracik;
					yKulka = j*kwadracik;
				}
			
			}
		}
	}






}
