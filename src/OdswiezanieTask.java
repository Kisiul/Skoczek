import java.util.Timer;
import java.util.TimerTask;

public class OdswiezanieTask extends TimerTask {
Plansza p;

OdswiezanieTask(Plansza plansz){
  p=plansz;
}
    public void run() {
      p.repaint();
    }
  }
