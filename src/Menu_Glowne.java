import javax.swing.*;
import java.awt.event.*;
import java.awt.Desktop;
import java.io.*;

/**
*	Menu Glowne.
*
*	Okno powitalne z menu glownym
*	Z niego sa wywolywane kolejne watki i metody
*/
public class Menu_Glowne {//implements Runnable{
	private Status status_gry;

	/**
	*	Konstruktor.
	*
	*	@param s Referencja do globalnego obiekto klasy status.
	*/
	Menu_Glowne(Status s){
		status_gry = s;
		Okno_Gry okno;
		Menu_Glowne_Rozpocznij();
		}

	/**
	*	Glowna funkcja menu.
	*
	*	Obsluga przyciskow menu i ich akcji.
	*	Wystepuje etykieta "synchronized" zeby ten kod mogl byc wykonywany tylko w jednym watku.
	*/
	public synchronized void Menu_Glowne_Rozpocznij(){
		JFrame okno = new JFrame("Menu Glowne");
		okno.setSize(400, 500);

		JButton nowa_gra = new JButton("Nowa Gra");
		nowa_gra.setBounds(150, 50, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
		nowa_gra.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
              	if(status_gry.stan_gry == Status.Stan.MENU_GL){ //nowa gra moze wystartowac tylko wtedy, kiedy nie trwa inna gra.
              		Okno_Gry panel_1 = new Okno_Gry(status_gry); 
					//Thread thread_panel_gry = new Thread(panel_1);
					status_gry.stan_gry = Status.Stan.PAUZA;
					okno.setVisible(false);

					//thread_panel_gry.start();
				}
              	
        	}  
    	});

		JButton najlepsze_wyniki = new JButton("Ranking");
		najlepsze_wyniki.setBounds(150, 100, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
		najlepsze_wyniki.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
              	if(status_gry.stan_gry == Status.Stan.MENU_GL){ //nowa gra moze wystartowac tylko wtedy, kiedy nie trwa inna gra.
              		Okno_Gry panel_1 = new Okno_Gry(status_gry); 
					//Thread thread_panel_gry = new Thread(panel_1);
					status_gry.stan_gry = Status.Stan.PAUZA;
					okno.setVisible(false);

					//thread_panel_gry.start();
				}
              	
        	}  
    	});

    	JButton pomoc = new JButton("Pomoc");
		pomoc.setBounds(150, 150, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
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

		JButton wyjscie = new JButton("Wyjscie");
		wyjscie.setBounds(150, 200, 100, 30);
		// przypisanie akcji do przycisku nowa gra.
		wyjscie.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
              	System.exit(0);
			}
    	});
		
		// jakies graficzne instrukcje.
		okno.add(nowa_gra);
		okno.add(najlepsze_wyniki);
		okno.add(pomoc);
		okno.add(wyjscie);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setLayout(null);
		okno.setVisible(true);
	}

	/**
	*	Funkcja do wywolania w nowym watku, implementuje interfejs Runnable.
	*
	*public void run(){
	*	Menu_Glowne_Rozpocznij();
	*}
	*/
}