package okna;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gra.Parsowanie;

public class Okno_Ranking {
		
	private JFrame okno_ranking;
	public Parsowanie pars;

	
	public Okno_Ranking()
	{
		pars = new Parsowanie();
		pars.loadProperties(new File("ranking.properties"));
		
		okno_ranking = new JFrame("ranking");// tworze za kazdym razem nowa ramke
		okno_ranking.setLocation(50, 0); //lokalizacja, docelowo pewnie jakis center, a nie wpsolrzedne
		okno_ranking.setSize(250, 400); //rozmiar
		okno_ranking.setVisible(true); //widoczne, przy zamykaniu ustawiane na fa³sz
		
		JLabel l_ranking = new JLabel(); //w label wpisywany bedzie ranking
		String st = new String("<html><p style=\"word-spacing: 1cm\">nr.         nick     czas\n<br><br>"); //nazwy kategorii w rankingu
		for (int i=1; i<=pars.parsuj("liczba_wynikow"); i++) // zaleznie ile jest najlepszych wynikow w pliku, tylu wpisujemy do stringa
			 st += (i +".    " + pars.parsuj_string(i+"_n") + "     " + pars.parsuj(i+"_czas")+ "<br>" );	// wpisywanie z pliku nr + nick+ czas
		
		st+="</p></html>";

		l_ranking.setBounds(40,20, 150, 200); //polozenie lebela
		l_ranking.setText(st); //wstawiamy tekst ustaliny w forze wy¿ej
		okno_ranking.add(l_ranking); //dodajemy label
		
		JButton b_wyjscie = new JButton("wyjscie"); 
		b_wyjscie.setBounds(75,250, 100, 30);
		okno_ranking.add(b_wyjscie);
		okno_ranking.setLayout(null);
		b_wyjscie.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				okno_ranking.setVisible(false); // wychodzimy, poprzez uniewidocznienie
	        }  
	    	});

		
	}

	
		

}
