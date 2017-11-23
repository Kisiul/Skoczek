import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Plansza extends JPanel {
	private Parsowanie pars;
	private int x;
	public Plansza() {
		
		//³aduje konfiguracje
		pars = new Parsowanie();
		pars.loadProperties();
		// rozmiar planszy odczytany z pliku
		setPreferredSize(new Dimension(pars.parsuj("rozmiar_planszy_x"), pars.parsuj("rozmiar_planszy_y")));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// rysuje wszystkie przeszkody, zale¿nie od parametru konfiguracyjnego z pliku: liczba przeszkod. Grubosc przeszkody ustalona arbitralnie na 12 nie wiadomo czemu
		for (int i=0; i<pars.parsuj("liczba_przeszkod"); i++)
		{
			g2d.fillRect(pars.parsuj("x"+(i+1)), pars.parsuj("y"+(i+1)), pars.parsuj("dl"+(i+1)), 12 );
		}
	}

}
