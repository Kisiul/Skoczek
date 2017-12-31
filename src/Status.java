
/**
* Klasa przechowujaca globalny status gry.
*
* Do synchronizacji i komunikacji miedzy watkami.
*/

public class Status{

/**
*	Dostepne stany gry.
*
*/
	public enum Stan{
		MENU_GL, GRA, PAUZA;
	}
/**
*	Aktualny stan gry.
*
*/	
	public static Stan stan_gry;
/**
*	Wspolrzedne gracza na ekranie
*/
	private static int[] pozycja_gracza;
	private static int[] predkosc_gracza;
	private static int bonus_jetpack;
	private static int bonus_mlotek;
	private static int bonus_niepamietam;
/**
*	Konstruktor
*
*/
	Status(){
		stan_gry = Stan.MENU_GL;
		pozycja_gracza = new int[2];
		pozycja_gracza[0] = 0;
		pozycja_gracza[1] = 0;
		predkosc_gracza = new int[2];
		predkosc_gracza[0] = 0;
		predkosc_gracza[1] = 0;
		bonus_jetpack = 0; //potem bedzie trzeba to tez sparsowac
		bonus_mlotek = 0;
		bonus_niepamietam = 0;
	}

	public synchronized void ustaw_pozycje(int x, int y){
		pozycja_gracza[0] = x;
		pozycja_gracza[1] = y;
	}

	public synchronized void ustaw_predkosc(int x, int y){
		predkosc_gracza[0] = x;
		predkosc_gracza[1] = y;
	}

	public synchronized void ustaw_bonusy(int j, int m, int n){
		bonus_jetpack = j;
		bonus_mlotek = m;
		bonus_niepamietam = n;
	}

	public synchronized Stan wez_stan_gry(){
		return stan_gry;
	}

	public synchronized int wez_pozycje_x(){
		return pozycja_gracza[0];
	}

	public synchronized int wez_pozycje_y(){
		return pozycja_gracza[1];
	}

	public synchronized int wez_predkosc_x(){
		return predkosc_gracza[0];
	}

	public synchronized int wez_predkosc_y(){
		return predkosc_gracza[1];
	}

	public synchronized int wez_jetpack(){
		return bonus_jetpack;
	}

	public synchronized int wez_mlotek(){
		return bonus_mlotek;
	}

	public synchronized int wez_niepamietam(){
		return bonus_niepamietam;
	}
}
