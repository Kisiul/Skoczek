import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Parsowanie {
		private static int liczba_przeszkod;
		private static int[] x;
		private static int[] y;
		private static int[] dl;
		private File f;
		private Properties properties;
		
		public Parsowanie()
		{
		    //Plik z konfiguracj¹
			f= new File("conf.properties");
			//przysz³y obiekt Properties
			properties = new Properties();
			
			System.setProperty("file.encoding", "UTF-8");
		}
	 


		/*public static void main(String[] args) {
	        
	        PropertiesTest pt = new PropertiesTest();
	        pt.loadProperties();
	        liczba_przeszkod = Integer.parseInt(properties.getProperty("liczba_przeszkod"));
	        x = new int[liczba_przeszkod];
	        y = new int[liczba_przeszkod];
	        dl = new int[liczba_przeszkod];
	        for (int i=0; i<liczba_przeszkod; i++)
	        {
	        	String ii = String.valueOf(i+1);
	        	x[i] = Integer.parseInt(properties.getProperty("x".concat(ii)));
	        	y[i] = Integer.parseInt(properties.getProperty("y".concat(ii)));
	        	dl[i] = Integer.parseInt(properties.getProperty("dl".concat(ii)));
	        }
	        System.out.println("x[0]: "+x[0]+"  y[0]: "+y[0]+"  dl[0]: "+dl[0]);
	        
	    
	        
	    }
	    */
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
	    public int parsuj(String s)
	    {
	    	return Integer.parseInt(properties.getProperty(s));
	    }
	    public Properties getProperties() {
			return properties;
		}
	
}
