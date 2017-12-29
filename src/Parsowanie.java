import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//<<<<<<< HEAD
/**
 * Klasa odczytujaca plik "properties" z konfiguracja gry
 * P�ki co czyta konkretny plik
 */
//=======


/**
*	Parsowanie.
*
*	Klasa zawierajaca metody do parsowania plikow konfiguracyjnych.
*/

public class Parsowanie {
		private int a;
		private File f;
		private Properties properties;

		
		/**
		 *Konstruktor
		 *bez parametrow 
		 */
//=======

/**
*	Konstruktor
*
*/

		public Parsowanie()
		{
		    //Plik z konfiguracj¹
			
			//przysz³y obiekt Properties
			properties = new Properties();
			System.setProperty("file.encoding", "UTF-8");
		}
	 




	    public void loadProperties(int numer_planszy){


	        //Strumieñ wejœciowy
	        InputStream is;
	        f= new File("plansza" + numer_planszy +".properties");
	        try {
	            is = new FileInputStream(f);
	            //³adujemy nasze ustawienia
	            properties.load(is);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	    *zwraca wartosc klucza properties, rzuca wyjatek, jesli zle podana nazwa klucza
	    */
	    private void parsujv(String s)
	    {
	    	try {
	    	a = Integer.parseInt(properties.getProperty(s));
	    	} catch (IllegalArgumentException a) {
	    		System.out.println("Sprawdz pisownie klucza properties "+ a);
	    	}	
	    }
	    /**
	    *wywoluje metode prywatana, bo nie moglem zroib dla returna try -kacza
	    */
		public int parsuj(String s)
	    {
	    	parsujv(s);
    		return a;
	    }
	   public Properties getProperties() {
			return properties;
		}
	
}
