
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
		pars.loadProperties();
	}

	public void uaktualnij_pozycje(){
		int x;
		int y;
		x = /*dlugie i skomplikowane obliczenia*/ 0;
		y = /*dlugie i skomplikowane obliczenia*/ 0;
		stan_gry.ustaw_pozycje(x, y);
	}

	public void uaktualnij_bonusy(){

	}

	public void uaktualnij_punkty(){

	}

	private void logika_rozpocznij(){
		//tutaj nalezaloby ustawic nasluchiwanie klawiszy itp.
	}

	public void run(){
		logika_rozpocznij();
		System.out.println("Logika ruszyla");
	}

	// TODO: Trzeba sie jeszcze zastanowic nad jakas funkcja wymuszajaca odswiezanie ekranu.
	// Proponuje, zeby byla wolana stad, ale nie jestem pewien.
	// public void odswiez_ekran(){...} ?
}
