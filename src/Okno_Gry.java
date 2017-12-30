import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

/**
* Glowny ekran gry.
*
* Zawiera panel gry- "plansza", oraz przyciski sterujace gra.
*/
public class Okno_Gry extends JFrame implements  KeyListener{//, Runnable{
	private Status status_gry;
	private Parsowanie pars;
	Logika logika;
	JFrame gra;
	private Plansza plansza;


	/** 
    * Konstruktor klasy.
    *
    * @param s Referencja do obiektu klasy Status.
    */
	public Okno_Gry(Status s){
		status_gry = s;
		pars = new Parsowanie();
		pars.loadProperties();
		
		logika = new Logika(s);
		plansza = new Plansza(s);
		//Thread thread_logika = new Thread(logika);
		//thread_logika.start();
		Panel_Gry_Rozpocznij();
	}

	/** 
    * Metoda tworzaca glowne okno gry, wywolywana z metody run().
    */
	public synchronized void Panel_Gry_Rozpocznij(){
		gra = new JFrame("Gra");
		
		//ustawienie rozmiarow okna do wielkosci panelu gry
		gra.setSize(pars.parsuj("rozmiar_planszy_x")+10, pars.parsuj("rozmiar_planszy_y")+10);
		
		JButton b_pauza = new JButton("pauza");
		b_pauza.setBounds(300,10, 100, 20);
		
    	// Akcja po wcisnieciu "pauza"
		b_pauza.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){  
				switch(status_gry.stan_gry){
				case GRA:
					status_gry.stan_gry = Status.Stan.PAUZA;	
					b_pauza.setText("pauza");
					gra.setFocusable(true);
					break;
				case PAUZA:
					status_gry.stan_gry = Status.Stan.GRA;
					b_pauza.setText("gra");
					gra.setFocusable(true);
					break;
				}
	        	System.out.println(status_gry.stan_gry);
        	}  
    	});


		// ustawienia okna

		gra.setLayout(new BorderLayout());
		gra.add(b_pauza, BorderLayout.PAGE_START);
		gra.add(plansza, BorderLayout.CENTER);
		gra.setFocusable(true);
		gra.addKeyListener(this);
		//gra.addKeyListener(panel);
		gra.addKeyListener(logika);


		pack();
		gra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gra.setVisible(true);
	}
	
	/** 
    * Metoda do wywolania w nowym watku.
    *
	public void run(){
		Panel_Gry_Rozpocznij();
	}*/

	/*@Override
	public void keyPressed(KeyEvent evt) {		
		switch(evt.getKeyCode())
		{
		case 37:
			panel.skoczek.setPozycja_skoczka_x(panel.skoczek.getPozycja_skoczka_x()-10);
			System.out.println("lewo");
			break;
		case 38:
			panel.skoczek.setPozycja_skoczka_y(panel.skoczek.getPozycja_skoczka_y()-10);
			break;
		case 39:
			panel.skoczek.setPozycja_skoczka_x(panel.skoczek.getPozycja_skoczka_x()+10);
			break;
		case 40:
			panel.skoczek.setPozycja_skoczka_y(panel.skoczek.getPozycja_skoczka_y()+10);
			break;
		default:
			System.out.println("asdf");
			break;
		}
		panel.repaint();
	}*/

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
		switch(evt.getKeyCode())
		{
		case 37:
			System.out.println("lewo");
			logika.lewo();
			break;
		case 38:
			System.out.println("gora");
			logika.gora();
			break;
		case 39:
			System.out.println("prawo");
			logika.prawo();
			break;
		case 40:
			System.out.println("dol");
			logika.dol();
			break;
		default:
			System.out.println("asdf");
			break;
		}
		plansza.repaint();
		
	}


}
