import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;

/**
* Glowny ekran gry.
*
* Zawiera panel gry, oraz przyciski sterujace gra.
*/
public class Panel_Gry extends JFrame implements Runnable{
	private Status status_gry;


	/** 
    * Konstruktor klasy.
    *
    * @param s Referencja do obiektu klasy Status.
    */
	public Panel_Gry(Status s){status_gry = s;}

	/** 
    * Metoda tworzaca glowne okno gry, wywolywana z metody run().
    */
	public synchronized void Panel_Gry_Rozpocznij(){
		JFrame gra = new JFrame("Gra");
		Plansza panel = new Plansza();
		
		//ustawienie rozmiarow okna do wielkosci panelu gry
		gra.setSize(panel.pars.parsuj("rozmiar_planszy_x")+10, panel.pars.parsuj("rozmiar_planszy_y")+10);
		
		JButton pauza = new JButton("pauza");
		pauza.setBounds(300,10, 100, 20);

		//pole tekstowe z aktualnym stanem gry, mozna wywalic.
		JTextField tf = new JTextField("stan gry");  
    	tf.setBounds(150,10, 100,20);


    	// Akcja po wcisnieciu "pauza"

		pauza.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent e){  
				switch(status_gry.stan_gry){
				case GRA:
					status_gry.stan_gry = Status.Stan.PAUZA;	
					tf.setText("pauza");
					break;
				case PAUZA:
					status_gry.stan_gry = Status.Stan.GRA;
					tf.setText("gra");
					break;
				}
	        	System.out.println(status_gry.stan_gry);
        	}  
    	});

		// ustawienia okna

		gra.setLayout(new BorderLayout());
		gra.add(pauza, BorderLayout.PAGE_START);
		gra.add(panel, BorderLayout.CENTER);
		gra.add(tf, BorderLayout.PAGE_END);

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
}
