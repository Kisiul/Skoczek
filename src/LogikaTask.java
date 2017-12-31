import java.util.Timer;
import java.util.TimerTask;

public class LogikaTask extends TimerTask {
Status stan_gry;

LogikaTask(Status s){
  stan_gry = s;
}
    public void run() {
      stan_gry.ustaw_pozycje(stan_gry.wez_pozycje_x() + stan_gry.wez_predkosc_x(), stan_gry.wez_pozycje_y() + stan_gry.wez_predkosc_y());
    }
  }
