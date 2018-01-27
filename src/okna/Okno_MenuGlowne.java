package okna;
import javax.swing.*;

import gra.Parsowanie;
import status.Status;

import java.awt.event.*;
import java.awt.Desktop;
import java.io.*;

/**
*	Menu Glowne.
*
*	Okno powitalne z menu glownym
*	Z niego sa wywolywane kolejne watki i metody
*/
public class Okno_MenuGlowne{
	private Status status_gry;
	public Parsowanie pars;
	/** Okno, zawierajace menu*/
	private JFrame okno_menu;
	/** Okno z plansza do gry*/
	private Okno_Gry okno_gry;
	/** Okno z najlepszymi wynikami*/
	private Okno_Ranking ranking;

	/**
	*	Konstruktor.
	*
	*	@param s Referencja do globalnego obiekto klasy status.
	*/
	public Okno_MenuGlowne(Status s){
		status_gry= s;
		Menu_Glowne_Rozpocznij();
		}

	/**
	*	Glowna funkcja menu.
	*
	*	Obsluga przyciskow menu i ich akcji.
	*	Wystepuje etykieta "synchronized" zeby ten kod mogl byc wykonywany tylko w jednym watku.
	*/
	public synchronized void Menu_Glowne_Rozpocznij(){
		
		pars = new Parsowanie();
		/** £aduje plik z najlepszymi wynikami*/
		pars.loadProperties(new File("ranking.properties"));
		
		okno_menu = new JFrame("Menu Glowne");
		okno_menu.setSize(400, 500);
		
		
// ======= 	NOWA GRA ======		
		
		/** Przycik sluzy do rozpoczecia rozgrywki. Po wcisnieciu pokazuje sie nowe okno*/
		JButton nowa_gra = new JButton("Nowa Gra");
		nowa_gra.setBounds(150, 50, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
		nowa_gra.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
              	if(status_gry.stan_gry == Status.Stan.MENU_GL){ //nowa gra moze wystartowac tylko wtedy, kiedy nie trwa inna gra.
  					status_gry.stan_gry = Status.Stan.GRA;
  					okno_gry = new Okno_Gry();  //tworzony jest nowy obiekt okno_gry
  					okno_gry.setVisibile(true); //okno ma byc widoczne
  					status_gry.getInstance().setPauza(false); 
				}
              	
        	}  
    	});
		
// ======= 	RANKING ======
		/** Przycisk sluzy do wyswietlenia okna z najlepszymi wynikami*/
		JButton najlepsze_wyniki = new JButton("Ranking");
		najlepsze_wyniki.setBounds(150, 100, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
		najlepsze_wyniki.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
              	if(status_gry.stan_gry == Status.Stan.MENU_GL){ //nowa gra moze wystartowac tylko wtedy, kiedy nie trwa inna gra.
              		ranking = new Okno_Ranking(); //utworzenie nowego okna z rankingiem
				}      	
        	}  
    	});
		
// ======= 	POMOC ======
		/** Przycisk s³uzy do wyswietlenia okna z zasadami gry*/
    	JButton pomoc = new JButton("Pomoc");
		pomoc.setBounds(150, 150, 100, 30);
		// przypisanie akcji do przycisku 
		pomoc.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
           	if (Desktop.isDesktopSupported()) {
   				try {
        			File myFile = new File("../Dokumentacja/pomoc.pdf");
        			Desktop.getDesktop().open(myFile);
    			} catch (IOException ex) {
        			System.out.println(ex);
    			}
			}
              	
        }  
    	});
// ======= 	WYJŒCIE ======
		/** Przycisk s³uzy do zakonczenia dzialania aplikacji*/
		JButton wyjscie = new JButton("Wyjscie");
		wyjscie.setBounds(150, 200, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
		wyjscie.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
              	System.exit(0);
			}
    	});
		
		// dodawanie komponentow graficznych do okna menu
		okno_menu.add(nowa_gra);
		okno_menu.add(najlepsze_wyniki);
		okno_menu.add(pomoc);
		okno_menu.add(wyjscie);
		// graficzne ustawienia
		okno_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno_menu.setLayout(null);
		okno_menu.setVisible(true);
	}
}