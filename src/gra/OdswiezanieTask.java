package gra;
import java.util.Timer;
import java.util.TimerTask;
import status.Status;
import okna.Plansza;


/**
 * Klasa, której zadaniem jest odświeżanie stanu gry i  ekranu.
 * @author      Radosław Maksymiuk i Krzystzof Dąbrowski
 */
public class OdswiezanieTask extends TimerTask {
/**
*Referencja do planszy
*/
Plansza p;
/**
*Referencja do statusu gry
*/
Status stan_gry;
/**
*Funkcja odświeżająca
*@param plansz referencja do planszy
*/
public OdswiezanieTask(Plansza plansz){
  p=plansz;
}
    public void run() {
    	if (!stan_gry.getInstance().getPauza()){
      stan_gry.getInstance().ustaw_czas((System.currentTimeMillis()- stan_gry.getInstance().wez_czas_start()));
      p.repaint();
    }
  }
}
