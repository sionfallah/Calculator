import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Calculator implements ActionListener {
    private JTextField display;
    private int num1 = 0;
    private int num2 = 0;
    private int operand;
    private int answer;

    public Calculator() {
        JFrame frame = new JFrame("Calculator");
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon("Calculator.png").getImage());

        display = new JTextField();
        display.setEditable(false);
        display.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JLabel name = new JLabel("(C) S. Fallah", JLabel.CENTER);
        frame.add(name, BorderLayout.SOUTH);

        JButton zero = new JButton("0");
        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");
        JButton six = new JButton("6");
        JButton seven = new JButton("7");
        JButton eight = new JButton("8");
        JButton nine = new JButton("9");
        JButton div = new JButton("/");
        JButton mult = new JButton("x");
        JButton add = new JButton("+");
        JButton sub = new JButton("-");
        JButton equal = new JButton("=");
        JButton clear = new JButton("C");

        JPanel keypad = new JPanel(new GridLayout(4, 4));
        keypad.add(seven);
        keypad.add(eight);
        keypad.add(nine);
        keypad.add(div);
        keypad.add(four);
        keypad.add(five);
        keypad.add(six);
        keypad.add(mult);
        keypad.add(one);
        keypad.add(two);
        keypad.add(three);
        keypad.add(sub);
        keypad.add(zero);
        keypad.add(clear);
        keypad.add(equal);
        keypad.add(add);

        frame.add(keypad, BorderLayout.CENTER);

        zero.addActionListener(this);
        one.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        add.addActionListener(this);
        sub.addActionListener(this);
        equal.addActionListener(this);
        div.addActionListener(this);
        mult.addActionListener(this);
        clear.addActionListener(this);

        frame.getRootPane().setDefaultButton(equal);
        clear.setMnemonic('C');

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // 11, 9, 8, 5
    }

    public void actionPerformed(ActionEvent e) {
        String key = e.getActionCommand();

        if ((e.getModifiers() & ActionEvent.CTRL_MASK) != 0) {
            if (key.equals("C")) {
                display.setText("");
                num1 = 0;
                num2 = 0;
                operand = 0;
                answer = 0;
            }
        } else {
            switch (key) {
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                    if (display.getText().length() < 8) {
                        display.setText(display.getText() + key);
                    }
                    break;
                case "+":
                    if (!display.getText().isEmpty()) {
                        num1 = Integer.parseInt(display.getText());
                        operand = 0;
                        display.setText("");
                    }
                    break;
                case "-":
                    if (!display.getText().isEmpty()) {
                        num1 = Integer.parseInt(display.getText());
                        operand = 1;
                        display.setText("");
                    }
                    break;
                case "x":
                    if (!display.getText().isEmpty()) {
                        num1 = Integer.parseInt(display.getText());
                        operand = 2;
                        display.setText("");
                    }
                    break;
                case "/":
                    if (!display.getText().isEmpty()) {
                        num1 = Integer.parseInt(display.getText());
                        operand = 3;
                        display.setText("");
                    }
                    break;
                case "=":
                    if (!display.getText().isEmpty()) {
                        num2 = Integer.parseInt(display.getText());
                        switch (operand) {
                            case 0:
                                answer = num1 + num2;
                                break;
                            case 1:
                                answer = num1 - num2;
                                break;
                            case 2:
                                answer = num1 * num2;
                                break;
                            case 3:
                                if (num2 != 0) {
                                    answer = num1 / num2;
                                } else {
                                    display.setText("Div by 0");
                                    return;
                                }
                                break;
                        }
                        String result = String.valueOf(answer);
                        if (result.length() > 8) {
                            display.setText("Overflow");
                        } else {
                            display.setText(result);
                        }
                    }
                    break;
                case "C":
                    String currentText = display.getText();
                    if (!currentText.isEmpty()) {
                        display.setText(currentText.substring(0, currentText.length() - 1));
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
