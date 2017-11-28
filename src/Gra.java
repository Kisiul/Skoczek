import java.awt.EventQueue;

/**
<<<<<<< HEAD
 * Glowna klasa gry
 * @author 4ntoni
 *
 */
public class Gra {

	public static void main(String[] args) {
		// tworze panel gry, ktory tworzy plansze, ktoraladuje plik konf i rysuje prostokaty
=======
*	Gra Skoczek.
*
*	Projekt programistyczny z przedmiotu PROZE.
*	Opiekun: Krystian Ignasiak
*
*	@author Radoslaw Maksymiuk
*	@author Krzysztof Dabrowski
*/
public class Gra {

	public static void main(String[] args) {
		//tworze panel gry, ktory tworzy plansze, ktoraladuje plik konf i rysuje prostokaty
>>>>>>> 02f596e0c82c80092102d0f330d3b1488ac6c4a9
		//docelowo panelgry trzeba uzupelnic o przyciski pauzy itp

		//Globalnie dostepny aktualny stan gry, do synchronizacji/komunikacji miedzy watkami.
		//mozna tutaj dodac takie rzeczy, jak wyliczona pozycja ludzika, etc...
		//narazie przechowuje tylko pauza lub gra.
		// !fajnie byloby to zrobic jako singleton!
		Status status_gry = new Status();

		//tworze panele
		Menu_Glowne menu_glowne = new Menu_Glowne(status_gry);


		//tworze watki
		Thread thread_menu_glowne = new Thread(menu_glowne);


		//uruchamiam watki
		thread_menu_glowne.start();


	}

}
