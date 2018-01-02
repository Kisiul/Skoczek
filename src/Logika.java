import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.concurrent.TimeUnit;

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

private boolean koniec;

Toolkit toolkit;
Timer timer_logiczny;

/**
*	Konstruktor.
*/
	Logika(Status s){
		stan_gry = s;
		pars = new Parsowanie();
		pars.loadProperties();

		koniec = false;

		toolkit = Toolkit.getDefaultToolkit();
		timer_logiczny = new Timer();
		timer_logiczny.schedule(new LogikaTask(this), 0, 1 * 20); //co 20ms uaktualniana bedzie pozycja gracza.
	}

	public void uaktualnij_pozycje(int x, int y){
		//x = /*dlugie i skomplikowane obliczenia*/ 0;
		//y = /*dlugie i skomplikowane obliczenia*/ 0;
		stan_gry.ustaw_pozycje(x, y);
	}

	public void uaktualnij_bonusy(){

	}

	public void uaktualnij_punkty(){

	}
	
	public void ruch()
	{ 
		//musi kazda przeszkode obadac za kazdym razem, wydaje sie malo optymalne, ale takie zycie
		for (int i=1; i<=pars.parsuj("liczba_przeszkod"); i++)
		{
		if (stan_gry.wez_predkosc_y()>0) //jak spada na przeszkode, to ma sie na niej zatrzymac, a jak od dolu to sie odbic
		{
			if (skoczek_lewo() < przeszkoda_prawo(i) && skoczek_prawo()> przeszkoda_lewo(i) && skoczek_dol()>przeszkoda_gora(i) && skoczek_gora()<przeszkoda_gora(i))
			{
				stan_gry.ustaw_predkosc(0, 0);
			}	
		}
		else
			if (skoczek_lewo() < przeszkoda_prawo(i) && skoczek_prawo()> przeszkoda_lewo(i) && skoczek_gora()<przeszkoda_dol(i) && skoczek_dol()>przeszkoda_dol(i))
					{
						stan_gry.ustaw_predkosc(stan_gry.wez_predkosc_x(), stan_gry.wez_predkosc_y()*-1);
					}
		//od bokow ma sie odbijac
		if (skoczek_dol() > przeszkoda_gora(i) && skoczek_gora() < przeszkoda_dol(i) && (skoczek_prawo() == przeszkoda_lewo(i)|| skoczek_lewo() == przeszkoda_prawo(i)))
		{
			stan_gry.ustaw_predkosc(stan_gry.wez_predkosc_x()*-1, stan_gry.wez_predkosc_y());
		}
		}
		stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x() + stan_gry.wez_predkosc_x(), stan_gry.wez_pozycje_y() + stan_gry.wez_predkosc_y()); 
		

	}
	
	public void lewo()
	{
		stan_gry.ustaw_predkosc(stan_gry.wez_predkosc_x()-1, stan_gry.wez_predkosc_y());
	}
	public void prawo()
	{
		stan_gry.ustaw_predkosc(stan_gry.wez_predkosc_x()+1, stan_gry.wez_predkosc_y());
	}
	public void gora()
	{
		stan_gry.ustaw_predkosc(stan_gry.wez_predkosc_x(), stan_gry.wez_predkosc_y()-1);
	}
	public void dol()
	{
			stan_gry.ustaw_predkosc(stan_gry.wez_predkosc_x(), stan_gry.wez_predkosc_y()+1);
	}


	public void logika_rozpocznij() {
		int x = 10; //pars.parsuj("pierwotna_pozycja_skoczka_x");
		int y = 10;//pars.parsuj("pierwotna_pozycja_skoczka_y");
		uaktualnij_pozycje(x, y);
	}

	public void pauza(){
		stan_gry.stan_gry = Status.Stan.GRA;
	}

	public void run(){
		logika_rozpocznij();
		System.out.println("Logika ruszyla");
	}

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

	@Override
	public void uaktualnij_pozycje() {
		// TODO Auto-generated method stub
		
	}
	public void zakoncz()
	{
		koniec = true;
	}
	
	// wspolrzedne y skoczka dolnej krawedzi
	public int skoczek_dol()
	{
		return stan_gry.wez_pozycje_y()+pars.parsuj("rozmiar_skoczka");
	}
	public int skoczek_gora()
	{
		return stan_gry.wez_pozycje_y();
	}
	public int skoczek_lewo()
	{
		return stan_gry.wez_pozycje_x();
	}
	public int skoczek_prawo()
	{
		return stan_gry.wez_pozycje_x()+pars.parsuj("rozmiar_skoczka");
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
	
	
	
	


}
