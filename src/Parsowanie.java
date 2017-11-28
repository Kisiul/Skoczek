import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Klasa odczytujaca plik "properties" z konfiguracja gry
 * Póki co czyta konkretny plik
 */
public class Parsowanie {
		private int a;
		private File f;
		private Properties properties;
		
		/**
		 *Konstruktor
		 *bez parametrow 
		 */
		public Parsowanie()
		{
		    //Plik z konfiguracj¹
			f= new File("conf.properties");
			//przysz³y obiekt Properties
			properties = new Properties();
			
			System.setProperty("file.encoding", "UTF-8");
		}
	 


	    public void loadProperties(){
	        //Strumieñ wejœciowy
	        InputStream is;
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
	    //zwraca wartosc klucza properties, rzuca wyjatek, jesli zle podana nazwa klucza
	    private void parsujv(String s)
	    {
	    	try {
	    	a = Integer.parseInt(properties.getProperty(s));
	    	} catch (IllegalArgumentException a) {
	    		System.out.println("Sprawdz pisownie klucza properties"+ a);
	    	}	
	    }
	    //wywoluje metode prywatana, bo nie moglem zroib dla returna try -kacza
	    public int parsuj(String s)
	    {
	    	parsujv(s);
    		return a;
	    }
	    public Properties getProperties() {
			return properties;
		}
	
}
