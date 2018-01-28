package gra;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;



/**
*	Parsowanie.
*	<p>
*	Klasa zawierajaca metody do parsowania plikow konfiguracyjnych.
*   <p>
*/
public class Parsowanie {

		private int a;
		private String st;
		private Properties properties;
		
		String plik;
		private int rozmiar1, rozmiar2;
		private int maxLevel;
		private int plansza[][] ;
/**
*	Konstruktor
*
*/
		public Parsowanie()
		{
		    //Plik z konfiguracją
			/**
			*przyszły obiekt Properties
			*/
			properties = new Properties();
			
			System.setProperty("file.encoding", "UTF-8");
		}
	 


		/**
		*Ładowanie właściwości
		*@param f referencja do pliku do wczytania.
		*/
	    public void loadProperties(File f){
	        //Strumieñ wejœciowy
	        InputStream is;
	        try {
	            is = new FileInputStream(f);
	            //ładujemy nasze ustawienia
	            properties.load(is);
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    /**
	    *Przypisuje wartosc klucza properties, rzuca wyjatek, jesli zle podana nazwa klucza
	    *@param s nazwa parsowanej właściwości.
	    *@throws exception
	    */
	    private void parsujv(String s)
	    {
	    	try {
	    	a = Integer.parseInt(properties.getProperty(s));
	    	} catch (IllegalArgumentException a) {
	    		System.out.println("Sprawdz pisownie klucza properties"+ a);
	    	}	
	    }
	    private void parsujvstring(String s)
	    {
	    	try {
	    	st = properties.getProperty(s);
	    	} catch (IllegalArgumentException a) {
	    		System.out.println("Sprawdz pisownie klucza properties"+ a);
	    	}	
	    }
	    /**
	    *Wywołuje metodę prywataną, bo nie moglem zrobić dla returna try -kacza.
	    *@param s nazwa parsowanej właściwości.
	    *@return wartość liczbowa parsowanej własności
	    */
		public int parsuj(String s)
	    {
	    	parsujv(s);
    		return a;
	    }
	      /**
	    *Wywołuje metodę prywataną, bo nie moglem zrobić dla returna try -kacza.
	    *@param s nazwa parsowanej właściwości.
	    *@return string - parsowany tekst
	    */
		public String parsuj_string(String s)
		{
			parsujvstring(s);
			return st;
		}
		/**
		*Zwraca referencję do obiektu Properties
		*@return zwracana referencja
		*/
	   public Properties getProperties() {
			return properties;
		}
	   
	   /** 
	   *metoda sluzy do odczytu ukladu planszy, zapisanego jako uklad zer, jedynek i jeszcze jakichs cyferek
	   *@param a wybór pliku konfiguracyjnego.
	   *@return tablica reprezentująca planszę
	   */
	   public int[][] odczytZPliku(int a)
		{
			//if (a<=maxLevel)
				plik = "konfigura"+a+".txt";
			
			
			File file = new File(plik);
		      try {
				Scanner in = new Scanner(file);
				//while (in.hasNextInt())
				//{
				rozmiar1 = in.nextInt();
				rozmiar2 = in.nextInt();
				plansza = new int [rozmiar1][rozmiar2];
				for (int i= 0; i<rozmiar1; i++)
				{
					for (int j=0; j< rozmiar2; j++)
					{
						plansza[i][j] = in.nextInt();
					}
				}
				//}
			      in.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return plansza;
		}
	   /**
	   *@return wysokość planszy
	   */
		public int getwysokosc()
		{
			return rozmiar1;
		}
		/**
		*@return wysokość planszy
		*/
		public int getszerokosc()
		{
			return rozmiar2;
		}
	
}
