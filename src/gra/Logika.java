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

		koniec = false;

		toolkit = Toolkit.getDefaultToolkit();
		timer_logiczny = new Timer();
		timer_logiczny.schedule(new LogikaTask(this), 0, 1 * 20); //co 20ms uaktualniana bedzie pozycja gracza.
	}



	public void uaktualnij_bonusy(){

	}

	public void uaktualnij_punkty(){

	}
	
	@Override
	public void uaktualnij_pozycje() 		
	{
		//musi kazda przeszkode obadac za kazdym razem, wydaje sie malo optymalne, ale takie zycie
		for (int i=1; i<=pars.parsuj("liczba_przeszkod"); i++)
		{
		if (stan_gry.getInstance().wez_predkosc_y()>0) //jak spada na przeszkode, to ma sie na niej zatrzymac, a jak od dolu to sie odbic
		{
			if (skoczek_lewo() < przeszkoda_prawo(i)-30 && skoczek_prawo()> przeszkoda_lewo(i)+30 && skoczek_dol()>przeszkoda_gora(i) && skoczek_gora()<przeszkoda_gora(i))
			{
				stan_gry.getInstance().ustaw_predkosc(0, 0);
				stan_gry.getInstance().ustaw_pozycje(stan_gry.getInstance().wez_pozycje_x(), przeszkoda_gora(i)-pars.parsuj("rozmiar_skoczka")-1);
			}	
		}
		else //jak od dolu to odbic
			if (skoczek_lewo() < przeszkoda_prawo(i)-10 && skoczek_prawo()> przeszkoda_lewo(i)+10 && skoczek_gora()<przeszkoda_dol(i) && skoczek_dol()>przeszkoda_dol(i))
					{
						stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_predkosc_y()*-1);
						//stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x() + stan_gry.wez_predkosc_x(), stan_gry.wez_pozycje_y() + stan_gry.wez_predkosc_y()+1); 

					}
		//od bokow ma sie odbijac
			else 
				if ((skoczek_dol() > przeszkoda_gora(i) && skoczek_gora() < przeszkoda_dol(i)) && 
				((skoczek_prawo() > przeszkoda_lewo(i) && skoczek_prawo() < przeszkoda_prawo(i))||( skoczek_lewo() < przeszkoda_prawo(i)&&skoczek_lewo() > przeszkoda_lewo(i))))
				{
					stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x()*-1, stan_gry.getInstance().wez_predkosc_y()*-1);
					System.out.println("boczek");
				}
		}
		stan_gry.getInstance().ustaw_pozycje(stan_gry.getInstance().wez_pozycje_x() + stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_pozycje_y() + stan_gry.getInstance().wez_predkosc_y()); 
		

	}
	
	/**
	*zwiększenie prędkości skoczka
	*/

	public void lewo()
	{
		stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x()-1, stan_gry.getInstance().wez_predkosc_y());
	}
	/**
	*zwiększenie prędkości skoczka
	*/
	public void prawo()
	{
		stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x()+1, stan_gry.getInstance().wez_predkosc_y());
	}
	/**
	*zwiększenie prędkości skoczka
	*/
	public void gora()
	{
		stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_predkosc_y()-1);
	}
	/**
	*zwiększenie prędkości skoczka
	*/
	public void dol()
	{
			stan_gry.getInstance().ustaw_predkosc(stan_gry.getInstance().wez_predkosc_x(), stan_gry.getInstance().wez_predkosc_y()+1);
	}


	public void logika_rozpocznij() {
		stan_gry.getInstance().ustaw_pozycje(pars.parsuj("pierwotna_pozycja_skoczka_x"), pars.parsuj("pierwotna_pozycja_skoczka_y"));
		stan_gry.getInstance().ustaw_predkosc(0,  0);
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
				System.out.println("lewo");
				lewo();
				break;
			case 38:	/*up*/
				System.out.println("gora");
				gora();
				break;
			case 39:	/*right*/
				System.out.println("prawo");
				prawo();
				break;
			case 40:	/*down*/
				System.out.println("dol");
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
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	/**
	*wspolrzedne skoczka dolnej krawedzi
	*
	*/
	public int skoczek_dol()
	{
		return stan_gry.getInstance().wez_pozycje_y()+pars.parsuj("rozmiar_skoczka");
	}
	public int skoczek_gora()
	{
		return stan_gry.getInstance().wez_pozycje_y();
	}
	public int skoczek_lewo()
	{
		return stan_gry.getInstance().wez_pozycje_x();
	}
	public int skoczek_prawo()
	{
		return stan_gry.getInstance().wez_pozycje_x()+pars.parsuj("rozmiar_skoczka");
	}

	/**
	* Obsługa przeszkód
	*/
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
	
	
	/**Metoda wywolana po wcisnieciu klawisza "pause" czyli zamrozenie rozgrywki*/
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
