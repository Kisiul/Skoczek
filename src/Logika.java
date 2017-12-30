import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
*	Klasa obliczajaca wszystko, co sie dzieje z graczem.
*	Wyniki swoich obliczen zapisuje w klasie Status.
*/
public class Logika implements Interfejs_Skoczka, KeyListener{

Status stan_gry;
Parsowanie pars;
/**
*	Konstruktor.
*/
	Logika(Status s){
		stan_gry = s;
		pars = new Parsowanie();
		pars.loadProperties();
	}

	public void uaktualnij_pozycje(int x, int y){
		//x = /*dlugie i skomplikowane obliczenia*/ 0;
		//y = /*dlugie i skomplikowane obliczenia*/ 0;
		stan_gry.ustaw_pozycje(x, y);
	}

	public void uaktualnij_bonusy(){

	}

	public void uaktualnij_punkty(){

	}
	public void lewo()
	{
		stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x()-5, stan_gry.wez_pozycje_y());
	}
	public void prawo()
	{
		stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x()+5, stan_gry.wez_pozycje_y());
	}
	public void gora()
	{
		stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x(), stan_gry.wez_pozycje_y()-5);
	}
	public void dol()
	{
		stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x(), stan_gry.wez_pozycje_y()+5);
	}

	private void logika_rozpocznij(){
		int x = pars.parsuj("pierwotna_pozycja_skoczka_x");
		int y = pars.parsuj("pierwotna_pozycja_skoczka_y");
		uaktualnij_pozycje(x, y);
	}

	public void run(){
		logika_rozpocznij();
		System.out.println("Logika ruszyla");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uaktualnij_pozycje() {
		// TODO Auto-generated method stub
		
	}


}
