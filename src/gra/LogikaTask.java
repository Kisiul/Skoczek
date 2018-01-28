package gra;
import java.util.Timer;
import java.util.TimerTask;
/**
* Obecnie nieużywane
*/


public class LogikaTask extends TimerTask {
Logika logika;

LogikaTask(Logika l){
  logika = l;
}
    public void run() {
    	  logika.uaktualnij_pozycje();
    }
  }
