
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
	public static int[] pozycja_gracza;
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
		bonus_jetpack = 0; //potem bedzie trzeba to tez sparsowac
		bonus_mlotek = 0;
		bonus_niepamietam = 0;
	}

	public synchronized void ustaw_pozycje(int x, int y){
		pozycja_gracza[0] = x;
		pozycja_gracza[1] = y;
	}

	public synchronized void ustaw_bonusy(int j, int m, int n){
		bonus_jetpack = j;
		bonus_mlotek = m;
		bonus_niepamietam = n;
	}

	public synchronized int wez_pozycje_x(){
		return pozycja_gracza[0];
	}

	public synchronized int wez_pozycje_y(){
		return pozycja_gracza[1];
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
