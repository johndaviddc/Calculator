import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorPanel extends JPanel {
	private static final long serialVersionUID = 1L;


    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JTextField display;
    private StringBuilder currentInput;
    
    public CalculatorPanel() {
        currentInput = new StringBuilder();
        setLayout(new BorderLayout());
        
        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 4));
        
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(e -> {
                currentInput.append(((JButton) e.getSource()).getText());
                display.setText(currentInput.toString());
            });
            buttonsPanel.add(numberButtons[i]);
        }

        operationButtons = new JButton[4];
        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < operations.length; i++) {
            operationButtons[i] = new JButton(operations[i]);
            operationButtons[i].addActionListener(new OperationAction());
            buttonsPanel.add(operationButtons[i]);
        }

        JButton equalsButton = new JButton("=");
        equalsButton.addActionListener(new EqualsAction());
        buttonsPanel.add(equalsButton);
        
        JButton clearButton = new JButton("C");
        clearButton.addActionListener(e -> {
            currentInput.setLength(0);
            display.setText("");
        });
        buttonsPanel.add(clearButton);
        
        add(buttonsPanel, BorderLayout.CENTER);
    }

    private class OperationAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentInput.append(" ").append(e.getActionCommand()).append(" ");
            display.setText(currentInput.toString());
        }
    }

    private class EqualsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] parts = currentInput.toString().split(" ");
            if (parts.length == 3) {
                double a = Double.parseDouble(parts[0]);
                double b = Double.parseDouble(parts[2]);
                String op = parts[1];
                double result = 0;

                try {
                    switch (op) {
                        case "+":
                            result = CalculatorOperations.add(a, b);
                            break;
                        case "-":
                            result = CalculatorOperations.subtract(a, b);
                            break;
                        case "*":
                            result = CalculatorOperations.multiply(a, b);
                            break;
                        case "/":
                            result = CalculatorOperations.divide(a, b);
                            break;
                    }
                    display.setText(String.valueOf(result));
                    currentInput.setLength(0);
                } catch (ArithmeticException ex) {
                    display.setText("Error");
                    currentInput.setLength(0);
                }
            }
        }
    }
}