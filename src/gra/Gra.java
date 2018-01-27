package gra;
import java.awt.EventQueue;

import okna.Okno_MenuGlowne;
import status.Status;

/**
*	Gra Skoczek.
*
*	Projekt programistyczny z przedmiotu PROZE.
*	Opiekun: Krystian Ignasiak
*
*	@author Radoslaw Maksymiuk
*	@author Krzysztof Dabrowski
*/
public class Gra {
	
	/**Globalnie dostepny stan gry*/
	Status status_gry;
	private Thread thread;
	private boolean running;
	/**G��wne okno, zawierajace menu. Otwierajace si� na poczatku gry*/
	private Okno_MenuGlowne okno_menu;

	/** Konstruktor klasy Gra*/
	public Gra()
	{
		/**Utworzenie instancji singletonu stan_gry*/
		status_gry.getInstance();
		
		okno_menu = new Okno_MenuGlowne(status_gry.getInstance());
	}
}
