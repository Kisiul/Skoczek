package gra;
import java.awt.EventQueue;

import okna.Okno_MenuGlowne;
import status.Status;

/**
*	Gra Skoczek.
*
*	Projekt programistyczny z przedmiotu PROZE.
*	Opiekun: Krystian Ignasiak <br>
*	Radosław Maksymiuk	<br>
*	Krzysztof Dąbrowski
*
*/
public class Gra {
	
	/**Globalnie dostepny stan gry*/
	Status status_gry;
	private Thread thread;
	private boolean running;
	/**Glowne okno, zawierajace menu. Otwierajace sie na poczatku gry*/
	private Okno_MenuGlowne okno_menu;

	/** Konstruktor klasy Gra*/
	public Gra()
	{
		/**Utworzenie instancji singletonu stan_gry*/
		status_gry.getInstance();
		
		okno_menu = new Okno_MenuGlowne(status_gry.getInstance());
	}
}
