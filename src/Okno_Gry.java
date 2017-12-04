import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

/**
* Glowny ekran gry.
*
* Zawiera panel gry- "plansza", oraz przyciski sterujace gra.
*/
public class Okno_Gry extends JFrame implements  Runnable, KeyListener{
	private Status status_gry;
	private Parsowanie pars;
	Logika logika;
	JFrame gra;
	Plansza panel;

	/** 
    * Konstruktor klasy.
    *
    * @param s Referencja do obiektu klasy Status.
    */
	public Okno_Gry(Status s){
		status_gry = s;
		pars = new Parsowanie();
		pars.loadProperties(1);
		
		Logika logika = new Logika(s);
		Thread thread_logika = new Thread(logika);
		thread_logika.start();

	}

	/** 
    * Metoda tworzaca glowne okno gry, wywolywana z metody run().
    */
	public synchronized void Panel_Gry_Rozpocznij(){
		gra = new JFrame("Gra");
		panel = new Plansza();
		
		//ustawienie rozmiarow okna do wielkosci panelu gry
		gra.setSize(600, 400);//pars.parsuj("rozmiar_planszy_x")+10, pars.parsuj("rozmiar_planszy_y")+10);
		
		JButton b_pauza = new JButton("pauza");
		b_pauza.setBounds(300,10, 100, 20);
		
		


    	// Akcja po wcisnieciu "pauza"
		b_pauza.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){  
				switch(status_gry.stan_gry){
				case GRA:
					status_gry.stan_gry = Status.Stan.PAUZA;	
					b_pauza.setText("pauza");
					break;
				case PAUZA:
					status_gry.stan_gry = Status.Stan.GRA;
					b_pauza.setText("gra");
					break;
				}
	        	System.out.println(status_gry.stan_gry);
        	}  
    	});


		// ustawienia okna

		gra.setLayout(new BorderLayout());
		gra.add(b_pauza, BorderLayout.PAGE_START);
		gra.add(panel, BorderLayout.CENTER);
		
		gra.setFocusable(true);
		gra.addKeyListener(this);

		pack();
		gra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gra.setVisible(true);
	}
	
	/** 
    * Metoda do wywolania w nowym watku.
    */
	public void run(){
		Panel_Gry_Rozpocznij();
	}

	@Override
	public void keyPressed(KeyEvent evt) {		
		/*switch(evt.getKeyCode())
		{
		case 37:
			logika.pisz();
			panel.
			break;
		case 38:
			logika.uaktualnij_pozycje(3);
			break;
		case 39:
			logika.uaktualnij_pozycje(2);
			break;
		case 40:
			logika.uaktualnij_pozycje(4);
			break;
		default:
			System.out.println("asdf");
			break;
		}*/
		//logika.uaktualnij_pozycje(evt);
		//System.out.println("skldfbls");
		//logika.pisz();
		panel.pisz();
		//panel.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
