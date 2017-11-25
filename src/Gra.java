import java.awt.EventQueue;

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

	public static void main(String[] args) {
		//tworze panel gry, ktory tworzy plansze, ktoraladuje plik konf i rysuje prostokaty
		//docelowo panelgry trzeba uzupelnic o przyciski pauzy itp

		//Globalnie dostepny aktualny stan gry, do synchronizacji/komunikacji miedzy watkami.
		//mozna tutaj dodac takie rzeczy, jak wyliczona pozycja ludzika, etc...
		//narazie przechowuje tylko pauza lub gra.
		Status status_gry = new Status();

		//tworze panele
		Menu_Glowne menu_glowne = new Menu_Glowne(status_gry);


		//tworze watki
		Thread thread_menu_glowne = new Thread(menu_glowne);


		//uruchamiam watki
		thread_menu_glowne.start();


	}

}
