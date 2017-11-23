
public class Gra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parsowanie parsowanie = new Parsowanie();
		parsowanie.loadProperties();
		System.out.println("liczba przeszkod: "+ parsowanie.parsuj("liczba_przeszkod"));

	}

}
