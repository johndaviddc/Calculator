import javax.swing.JFrame;

public class CalculatorFrame extends JFrame {
	public CalculatorFrame() {
		setTitle("Calculator");
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CalculatorPanel panel = new CalculatorPanel();
		add(panel);
	}
}