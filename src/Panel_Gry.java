import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_Gry extends JFrame{
	public Panel_Gry()
	{
		JPanel panel = new Plansza();

		add(panel);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
