import java.awt.event.KeyEvent;

/**
*	Klasa obliczajaca wszystko, co sie dzieje z graczem.
*	Wyniki swoich obliczen zapisuje w klasie Status.
*/
public class Logika implements Interfejs_Skoczka, Runnable{

Status stan_gry;
Parsowanie pars;
/**
*	Konstruktor.
*/
	Logika(Status s){
		stan_gry = s;
		pars = new Parsowanie();
		pars.loadProperties(1);
	}

	public void uaktualnij_pozycje(KeyEvent evt){
		/*int x = 0;
		int y=0;
		switch(i)
		{
		case 1:
			x= stan_gry.wez_pozycje_x()-10;
			break;
		case 2:
			x= stan_gry.wez_pozycje_x()+10;
			break;
		case 3:
			x= stan_gry.wez_pozycje_y()-10;
			break;
		case 4:
			x= stan_gry.wez_pozycje_y()+10;
			break;
			default:
				break;
		}
		stan_gry.ustaw_pozycje(x, y);
		*/
		System.out.println("skldfbls");
	}

	public void uaktualnij_bonusy(){

	}

	public void uaktualnij_punkty(){

	}
	public void laduj_z_pliku_na_poczatku_poziomu()
	{
		stan_gry.ustaw_pozycje(pars.parsuj("pierwotna_pozycja_skoczka_x"), pars.parsuj("pars.parsuj(pierwotna_pozycja_skoczka_y"));
		stan_gry.ustaw_bonusy(pars.parsuj("bonus_jestpack"), pars.parsuj("bonus_mlotek"), pars.parsuj("bonus_niepamietam"));
	}

	private void logika_rozpocznij(){
		//tutaj nalezaloby ustawic nasluchiwanie klawiszy itp.
	}

	public void run(){
		logika_rozpocznij();
		System.out.println("Logika ruszyla");
	}
	public void pisz()
	{
		System.out.println("skldfbls");
	}

	// TODO: Trzeba sie jeszcze zastanowic nad jakas funkcja wymuszajaca odswiezanie ekranu.
	// Proponuje, zeby byla wolana stad, ale nie jestem pewien.
	// public void odswiez_ekran(){...} ?
}
