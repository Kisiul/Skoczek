import javax.swing.*;
import java.awt.event.*;


/**
*	Menu Glowne.
*
*	Okno powitalne z menu glownym
*	Z niego sa wywolywane kolejne watki i metody
*/
public class Menu_Glowne implements Runnable, KeyListener{
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
		okno.setSize(400, 500);
		JButton nowa_gra = new JButton("Nowa Gra");
		nowa_gra.setBounds(150, 50, 100, 30);
		
		JTextField tf  = new JTextField("sfsf");
		tf.setBounds(300, 40, 100, 20);
		
		// przypisanie akcji do przycisku nowa gra.
		nowa_gra.addActionListener(new ActionListener(){  
		
		public void actionPerformed(ActionEvent e){  
              	if(status_gry.stan_gry == Status.Stan.MENU_GL){ //nowa gra moze wystartowac tylko wtedy, kiedy nie trwa inna gra.
              		Okno_Gry panel_1 = new Okno_Gry(status_gry); 
					Thread thread_panel_gry = new Thread(panel_1);
					status_gry.stan_gry = Status.Stan.PAUZA;
					thread_panel_gry.start();
				}
        	}  
    	});
		
		// jakies graficzne instrukcje.
		okno.add(nowa_gra);
		//okno.add(tf);
		//okno.setFocusable(true);
		//okno.addKeyListener(this);
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

	@Override
	public void keyPressed(KeyEvent arg0) {
System.out.println("lkjhgfdsa");		
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