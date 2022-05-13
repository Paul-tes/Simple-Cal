import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.concurrent.ExecutionException;

public class Simple_Cal extends JFrame {
    private JTextField text;
    private JPanel jp;
    private JButton[] btn = new JButton[16];
    private String[] opp = {"1","2","3","+","4","5","6","-","7","8","9","/","0",".","=","*"};
    Simple_Cal() {
        setTitle("Calculator");
        setBounds(400,200,300,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container c_p = getContentPane();
        text = new JTextField();
        c_p.setLayout(new BorderLayout());
        c_p.add(text, BorderLayout.NORTH);

        for (int i = 0; i < 16; i++) {
            btn[i] = new JButton(opp[i]);
        }
        jp = new JPanel(new GridLayout(4,4));
        Listener l = new Listener();
        for (int i = 0; i < 16; i++) {
            jp.add(btn[i]);
            btn[i].addActionListener(l);
        }
        c_p.add(jp, BorderLayout.CENTER);


    }

    private class Listener implements ActionListener {
        double x , y , res;
        String xx="", yy="", rr="";
        String operation = "";

        @Override

        public void actionPerformed(ActionEvent e) {
            String op = e.getActionCommand();

            if (op =="."||op=="0"||op=="1"||op=="2"||op=="3"||op=="4"|| op=="5"||op=="6"||op=="7"||op=="8"||op=="9") {
                if (operation== "") {
                    xx = xx + op;;
                    text.setText(xx);
                }else {
                    yy = yy+op;
                    text.setText(yy);
                }
            } else if (op=="+"||op=="-"||op=="*"||op=="/") {
                if (xx == "") {
                    text.setText("");
                    text.setText("number first");
                }else if (operation == ""){ // if operation is not reserve by any operator,
                    operation = op;
                    text.setText(operation);
                }
            } else if (op == "=") {
                if (operation != "") { //check whether the operation is provided or not.
                    try {
                        res = result();
                        text.setText("");
                        rr = String.valueOf(res);
                        text.setText(rr);
                    }catch (NumberFormatException exe) {
                        text.setText("");
                        text.setText("Invalid input only number");
                    }catch (Exception exe)  {
                        exe.getStackTrace();
                    }

                }
            }
        }

        double result() {
            x = Double.parseDouble(xx);
            y = Double.parseDouble(yy);
            if (operation == "+") return x + y;
            else if (operation == "-") return x - y;
            else if (operation == "*") return x * y;
            else if (operation == "/") return x / y;
            else return 0.0;
        }
    }
}
