
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

/**
*	Konstruktor
*
*/
	Status(){
		stan_gry = Stan.MENU_GL;
		pozycja_gracza = new int[2];
		pozycja_gracza[0] = 0;
		pozycja_gracza[1] = 0;
	}

}