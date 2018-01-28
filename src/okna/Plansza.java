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

		rysujPlansze(1, g);
		g.setColor(Color.red);
		g.fillRect(stan_gry.wez_pozycje_x(), stan_gry.wez_pozycje_y(), stan_gry.getInstance().getKwadracik(), stan_gry.getInstance().getKwadracik());
		
		l_czas.setText(Long.toString(stan_gry.wez_czas()/1000));
	}
	
/** rysuje kwadraty zgodnie z plikiem konfigura, skoczek rysowany jest oddzielnie w paintcomponent*/	
	public void rysujPlansze(int num, Graphics g)
	{
		//Graphics2D g2d = (Graphics2D) g;		
		for (int j= 0; j<12; j++)
		{
			for (int i= 0; i<12; i++)
			{
				if (stan_gry.getInstance().getPlansza()[j][i] == 0)
				{
					g.setColor(Color.GRAY);
				}
				if (stan_gry.getInstance().getPlansza()[j][i] == 2)
				{
					g.setColor(Color.blue);
				}
				if ((stan_gry.getInstance().getPlansza()[j][i] == 1) ||(stan_gry.getInstance().getPlansza()[j][i] == 3) )
				{
					g.setColor(Color.WHITE);
				}
				if (stan_gry.getInstance().getPlansza()[j][i] == 4)
				{
					g.setColor(Color.yellow);
				}
				if (stan_gry.getInstance().getPlansza()[j][i] == 5)
				{
					g.setColor(Color.BLACK);
				}
				

				g.fillRect(i*stan_gry.getInstance().getKwadracik(), j*stan_gry.getInstance().getKwadracik(), stan_gry.getInstance().getKwadracik(), stan_gry.getInstance().getKwadracik());		
			}
		}
	}






}
