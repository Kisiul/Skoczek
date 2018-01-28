package gra;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.concurrent.TimeUnit;

import status.Status;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;


/**
*	Klasa obliczajaca wszystko, co sie dzieje z graczem.
*	Wyniki swoich obliczen zapisuje w klasie Status.
*/
public class Logika implements Interfejs_Skoczka, KeyListener{

Status stan_gry;
Parsowanie pars;
private File f;
private int tmp_x, tmp_y, tmp_pred_x, tmp_pred_y;
private long czas_start;
private int licznik_zoltych;
private int[] tmp_zolte;

private boolean koniec;

Toolkit toolkit;
Timer timer_logiczny;

/**
*	Konstruktor.
*/
	public Logika(){
		
		f= new File("conf.properties");
		pars = new Parsowanie();
		pars.loadProperties(f);
		licznik_zoltych =0;
		tmp_zolte = new int[2];
		tmp_zolte[0] = 2;
		tmp_zolte[1] = 2;
		logika_rozpocznij();
		koniec = false;

		toolkit = Toolkit.getDefaultToolkit();
		timer_logiczny = new Timer();
		timer_logiczny.schedule(new LogikaTask(this), 0, 1 * 20); //co 20ms uaktualniana bedzie pozycja gracza.
		
		
	}



	public void uaktualnij_bonusy(){

	}

	public void uaktualnij_punkty(){

	}
	
	/** glowna metoda obslugujaca ruch i kolizje*/
	@Override
	public void uaktualnij_pozycje() 	
	{
		
		wygrywalnosc();
		gdy_sobie_spada();
		krawedzie();
		stan_gry.getInstance().ustaw_pozycje(stan_gry.getInstance().wez_pozycje_x() + stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_pozycje_y() + stan_gry.getInstance().wez_predkosc_y());	

	}
	/** metoda obsluguje zderzenie z krawedziami bocznymi (cyfra 5 w macierzy)*/
	public void krawedzie()
	{
		int a = skoczek_dol()/30;  
		int b = skoczek_polowa()/30;
		int c = skoczek_polowa_poziom()/30;
		int d = skoczek_prawo()/30;
		int e = skoczek_lewo()/30;
		if (stan_gry.getInstance().getPlansza()[a][b] == 5)
		{
			System.out.println("skucha");
			stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), 0);
		}
		if (stan_gry.getInstance().getPlansza()[c][d] == 5 || stan_gry.getInstance().getPlansza()[c][e] == 5)
			stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x()*-1, stan_gry.getInstance().wez_predkosc_y());

		
	}
	/** metoda obsluguje chodzenie po podestach (cyfra 0 w macierzy, 4- podswietlenie na zolto)*/

	public void gdy_sobie_spada()
	{
		stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), 2);
		stan_gry.getInstance().getPlansza()[1][0] = 5;
		
		//ziomek na kafelku		
		int a = skoczek_dol()/30;  
		int b = skoczek_polowa()/30;
		int c = skoczek_lewo1()/30;
		if (stan_gry.getInstance().getPlansza()[a][b] == 0 || stan_gry.getInstance().getPlansza()[a][b] == 4) 
		{
			licznik_zoltych++;
			//System.out.println("liczba zoltych: "+ licznik_zoltych);
			if (licznik_zoltych>1)
			{
				int q = tmp_zolte[0];
				int w = tmp_zolte[1];
				stan_gry.getInstance().getPlansza()[q][w] = 1;
				licznik_zoltych--;
			}
			stan_gry.getInstance().getPlansza()[a][b] = 4;
			stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), 0);
			tmp_zolte[0]=a;
			tmp_zolte[1]=b;
			
		}
		else if (licznik_zoltych>0 && stan_gry.getInstance().wez_predkosc_y()>0)
		{
			int q = tmp_zolte[0];
			int w = tmp_zolte[1];
			stan_gry.getInstance().getPlansza()[q][w] = 1;
			licznik_zoltych--;
		}	
		if (stan_gry.getInstance().getPlansza()[a][b] == 4) 
			stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), 0);		
	}
	/**metoda obsluguje dotarcie do celu*/
	public void wygrywalnosc()
	{
		int b = skoczek_polowa()/30;
		int c = skoczek_polowa_poziom()/30;
		if (stan_gry.getInstance().getPlansza()[c][b] == 2)
		{
			System.out.println("WYGRYWALNOSC");
		}
		
		
		
	}
	

	public void lewo()
	{
		stan_gry.getInstance().ustaw_predkosc(-2, stan_gry.getInstance().wez_predkosc_y());
	}
	public void prawo()
	{
		stan_gry.getInstance().ustaw_predkosc(2, stan_gry.getInstance().wez_predkosc_y());
	}
	
	public void lewo_k()
	{
		stan_gry.getInstance().ustaw_predkosc(0, stan_gry.getInstance().wez_predkosc_y());
	}
	public void prawo_k()
	{
		stan_gry.getInstance().ustaw_predkosc(0, stan_gry.getInstance().wez_predkosc_y());
	}
	public void gora()
	{
		stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_predkosc_y()-1);
	}
	public void dol()
	{
			stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_predkosc_y()+1);
	}


	public void logika_rozpocznij() {
		stan_gry.getInstance().ustaw_predkosc(0,  0);
		System.out.println("ustawiam tu sobie npwa plansze");
		stan_gry.getInstance().setPlansza(pars.odczytZPliku(1));
		
		int szerokosc = pars.getszerokosc();
		int wysokosc = pars.getwysokosc();
		stan_gry.getInstance().setKwadracik(400/(wysokosc+1));
	
		System.out.println("kwadracik: " +stan_gry.getInstance().getKwadracik());
		for (int j= 0; j<12; j++)
		{
			for (int i= 0; i<12; i++)
			{if (stan_gry.getInstance().getPlansza()[j][i] == 3) 
					stan_gry.getInstance().ustaw_pozycje(i*stan_gry.getInstance().getKwadracik(),j*stan_gry.getInstance().getKwadracik());
			}
		}
		
	}

	public void pauza(){
		stan_gry.stan_gry = Status.Stan.GRA;
	}

	public void run1(){
		logika_rozpocznij();
		System.out.println("Logika ruszyla");
	}
/** keylistener obslugujacy ruch ludzika sterowany strzalkami*/
	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		//System.out.println("logika");
				int keycode = evt.getKeyCode();
			switch(keycode)
			{
			case 37:	/*left*/
				lewo();
				break;
			case 38:	/*up*/
				gora();
				break;
			case 39:	/*right*/
				prawo();
				break;
			case 40:	/*down*/
				dol();
				break;
			case 27:	/*escape*/
				System.out.println("escape");
				pauza();
				break;
			default:
				System.out.println(keycode);
				toolkit.beep();
				break;
			}
	}

	@Override
	public void keyReleased(KeyEvent evt) {
		// TODO Auto-generated method stub
		int keycode = evt.getKeyCode();
		switch(keycode)
		{
		case 37:	/*left*/
			lewo_k();
			break;
		case 39:	/*right*/
			prawo_k();
			break;
			default:
				break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	// wspolrzedne y skoczka dolnej krawedzi
	public int skoczek_polowa()
	{
		return stan_gry.getInstance().wez_pozycje_x()+15;
	}
	public int skoczek_polowa_poziom()
	{
		return stan_gry.getInstance().wez_pozycje_y()+15;
	}
	public int skoczek_dol()
	{
		return stan_gry.getInstance().wez_pozycje_y()+30;
	}
	public int skoczek_gora()
	{
		return stan_gry.getInstance().wez_pozycje_y();
	}
	public int skoczek_lewo()
	{
		return stan_gry.getInstance().wez_pozycje_x();
	}
	public int skoczek_lewo1()
	{
		return stan_gry.getInstance().wez_pozycje_x()+10;
	}
	public int skoczek_prawo()
	{
		return stan_gry.getInstance().wez_pozycje_x()+30;
	}
	public int skoczek_prawo1()
	{
		return stan_gry.getInstance().wez_pozycje_x()+20;
	}

	public int przeszkoda_dol(int i)
	{
		return pars.parsuj("y"+i)+12;
	}
	public int przeszkoda_gora(int i)
	{
		return pars.parsuj("y"+i);
	}
	public int przeszkoda_prawo(int i)
	{
		return pars.parsuj("x"+i)+pars.parsuj("dl"+i);
	}
	public int przeszkoda_lewo(int i)
	{
		return pars.parsuj("x"+i);
	}
	
	
	/**Metoda wywolana po wcisnieciu klawisza "pause" czyli zamro¿enie rozgrywki*/
	public void pause()
	{
		tmp_pred_x = stan_gry.getInstance().wez_predkosc_x(); // zapis predkosci, zeby po playu byly dokladnie te same parametry
		tmp_pred_y = stan_gry.getInstance().wez_predkosc_y(); // zapis predkosci, zeby po playu byly dokladnie te same parametry
		tmp_x = stan_gry.getInstance().wez_pozycje_x(); // zapis pozycji, zeby po playu byly dokladnie te same parametry
		tmp_y = stan_gry.getInstance().wez_pozycje_y(); // zapis pozycji, zeby po playu byly dokladnie te same parametry

		stan_gry.getInstance().ustaw_predkosc(0, 0); // zatrzymanie pedzacego ludzika
		stan_gry.stan_gry = Status.Stan.PAUZA;		
		stan_gry.getInstance().setPauza(true);
	}
	/** Metoda wywolana po wcisnieciu klawisza "play"- czyli powrot do gry po pauzie*/
	public void play()
	{	
		stan_gry.getInstance().setPauza(false);
		ustaw_czas_start(System.currentTimeMillis() - stan_gry.getInstance().wez_czas()); // chodzi o to, zeby czas startowal od tego zapauzowanego
		stan_gry.getInstance().ustaw_predkosc(tmp_pred_x, tmp_pred_y); //predkosc ma byc taka, jak przy pauzowaniu
		stan_gry.getInstance().ustaw_pozycje(tmp_x, tmp_y); // pozycja ma byc taka, jak przy pauzowaniu
		stan_gry.stan_gry = Status.Stan.GRA; // stan gra
	}
	/** Ustawienie czasu poczatkowego. Czas gry liczony bedzie poprzez roznice z czasem current*/
	public void ustaw_czas_start(long l)
	{
		stan_gry.getInstance().ustaw_czas_start(l);
	}
	
	
	
	


}
