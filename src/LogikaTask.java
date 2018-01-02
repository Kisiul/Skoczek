import java.util.Timer;
import java.util.TimerTask;

public class LogikaTask extends TimerTask {
Logika logika;

LogikaTask(Logika l){
  logika = l;
}
    public void run() {
    	  logika.ruch();
    }
  }
