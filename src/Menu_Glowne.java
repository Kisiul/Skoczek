import javax.swing.*;
import java.awt.event.*;


/**
*	Menu Glowne.
*
*	Okno powitalne z menu glownym
*	Z niego sa wywolywane kolejne watki i metody
*/
public class Menu_Glowne implements Runnable{
	private Status status_gry;
	

	/**
	*	Konstruktor.
	*
	*	@param s Referencja do globalnego obiekto klasy status.
	*/
	Menu_Glowne(Status s){status_gry = s;}

	/**
	*	Glowna funkcja menu.
	*
	*	Obsluga przyciskow menu i ich akcji.
	*	Wystepuje etykieta "synchronized" zeby ten kod mogl byc wykonywany tylko w jednym watku.
	*/
	public synchronized void Menu_Glowne_Rozpocznij(){
		JFrame okno = new JFrame("Menu Glowne");
		okno.setSize(400, 600);
		JButton nowa_gra = new JButton("Nowa Gra");
		nowa_gra.setBounds(150, 50, 100, 30);
		
		// przypisanie akcji do przycisku nowa gra.
		nowa_gra.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
              	if(status_gry.stan_gry == Status.Stan.MENU_GL){ //nowa gra moze wystartowac tylko wtedy, kiedy nie trwa inna gra.
              		Panel_Gry panel_1 = new Panel_Gry(status_gry); 
					Thread thread_panel_gry = new Thread(panel_1);
					status_gry.stan_gry = Status.Stan.PAUZA;
					thread_panel_gry.start();
				}
        	}  
    	});
		
		// jakies graficzne instrukcje.
		okno.add(nowa_gra);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setLayout(null);
		okno.setVisible(true);
	}

	/**
	*	Funkcja do wywolania w nowym watku, implementuje interfejs Runnable.
	*/
	public void run(){
		Menu_Glowne_Rozpocznij();
	}

}