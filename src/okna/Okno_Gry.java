package okna;
import javax.swing.*;

import gra.Logika;
import gra.OdswiezanieTask;
import gra.Parsowanie;
import status.Status;
import status.Status.Stan;

import java.awt.event.*;
import java.io.File;
import java.awt.BorderLayout;
import java.util.Timer;
import java.util.TimerTask;

/**
* Glowny ekran gry.
*
* Zawiera panel gry- "plansza", oraz przyciski sterujace gra.
*/
public class Okno_Gry implements  KeyListener{//, Runnable{
	
	/**okno z plansza fo gry*/
	private JFrame okno_gry;
	/** globalnie dostepny stan gry*/
	private Status status_gry;
	/** klasa pobierajca dane z pliku*/
	private Parsowanie pars;
	/** plik konfiguracyjny*/
	private File f;
	
	Timer odswiezanie;
	/** Jpanel z sama plansza*/
	private Plansza plansza;
	/** klasa realizujaca legike gry, obliczenia*/
	private Logika logika;
	/** zmienna trzymajaca czas podczas pauzy*/
	private long czas_tmp;
	



	/** 
    * Konstruktor klasy.
    *
    * 
    */
	public Okno_Gry(){
		
		okno_gry = new JFrame("Gra");
		
		f= new File("conf.properties"); // plik z konfiguracja planszy
		pars = new Parsowanie();
		pars.loadProperties(f);
		logika = new Logika();
		logika.ustaw_czas_start(System.currentTimeMillis()); //podczas tworzenia obiektu okno_gry wlaczamy licznik czasu- pomiar potrzebny do punktacji
		logika.logika_rozpocznij();
		
		plansza = new Plansza(Status.getInstance());

		odswiezanie = new Timer();
		odswiezanie.schedule(new OdswiezanieTask(plansza), 0, //initial delay
        1 * 20);//plansza.repaint() raz na 20ms, powinno wyjsc 50Hz.

		Panel_Gry_Rozpocznij();
	}

	/** 
    * Metoda tworzaca glowne okno gry, wywolywana z metody run().
    */
	public synchronized void Panel_Gry_Rozpocznij(){
		
		//ustawienie rozmiarow okna do wielkosci panelu gry
		okno_gry.setSize(pars.parsuj("rozmiar_planszy_x")*2+10, pars.parsuj("rozmiar_planszy_y")+10);
		
		/**przycisk zatrzymujacy gre, wstzrymuje ruch oraz zliczanie czasu, po wcisnieciu zmienia sie w przycisk "gra", pozwalajacy na powrot do gry*/
		JButton b_pauza = new JButton("pauza");
		b_pauza.setBounds(300,10, 100, 20);
		b_pauza.setFocusable(false);
/** przycisk konczy rozgrywke i powoduje powrot do menu*/
		JButton b_wyjscie = new JButton("Wyjdz");
		
		
		
		
		// ustawienia okna
		okno_gry.setLayout(new BorderLayout());
		
		// dodawanie komponentow graficznych do okna: przyciskow oraz panelu z plansza
		okno_gry.add(b_pauza, BorderLayout.PAGE_START);
		okno_gry.add(b_wyjscie, BorderLayout.SOUTH);
		okno_gry.add(plansza, BorderLayout.CENTER);

		okno_gry.setFocusable(true);
		/** dadanie sluchacza przyciskow od razu w klasie "logika" w celu obslugi ruchu postaci sterowanego strzalkami*/
		okno_gry.addKeyListener(logika);

		okno_gry.pack();
		okno_gry.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno_gry.setVisible(true);
		
    	// Akcja po wcisnieciu "pauza"
		b_pauza.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){ 
				switch(status_gry.stan_gry){
				case GRA:	
					b_pauza.setText("gra");
					logika.pause(); 
					break;
				case PAUZA:
					b_pauza.setText("pauza");
					logika.play();
					break;
				}
	        	System.out.println(status_gry.stan_gry);
			}
    	});
		//Akcja po wcinieciu "wyjscie"
		b_wyjscie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){ 
				System.out.println("wyjscie");
				status_gry.stan_gry = Status.stan_gry.MENU_GL;
				okno_gry.setVisible(false);	
			}
		});
	}


		@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent evt) {
		System.out.print("ojej");

	}
	public void setVisibile(boolean b)
	{
		okno_gry.setVisible(b);
	}

	
}
