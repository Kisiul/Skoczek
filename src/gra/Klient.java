package gra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

/**
 * Klient komunikacji sieciowej
 */
public class Klient {
private Socket s;
private PrintWriter out;
private BufferedReader input;
    /**
     * konstruktor
     */
    public Klient() throws IOException {
        String serverAddress = JOptionPane.showInputDialog(
            "Podaj adres IP serwera skoczka\n" );
        s = new Socket(serverAddress, 9090);
         input = new BufferedReader(new InputStreamReader(s.getInputStream()));

            out = new PrintWriter(s.getOutputStream(), true);

        String answer = input.readLine();
        JOptionPane.showMessageDialog(null, answer);
        parsuj("liczba_przeszkod");
    }
    /**
	*Popros serwer o parsowanie wlasciwosci
	*@param s nazwa
    */
    public String parsuj(String s){
    	out.println("parsuj");
    	out.println(s);
    	try{
    		return input.readLine();
    	}catch(IOException ex){
    		System.out.println(ex);
    	}
    	return "error";
    }
}
