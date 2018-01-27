package gra;
import java.util.Timer;
import java.util.TimerTask;
import status.Status;
import okna.Plansza;

public class OdswiezanieTask extends TimerTask {
Plansza p;
Status stan_gry;

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
