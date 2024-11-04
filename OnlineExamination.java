import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class Login extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel, newPanel2;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    Login() {
        JLabel userLabel = new JLabel("                        Username");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        textField1 = new JTextField(15);
        Font bigFont1 = textField1.getFont().deriveFont(Font.PLAIN, 40f);
        textField1.setFont(bigFont1);
        JLabel passLabel = new JLabel("                        Password");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        textField2 = new JPasswordField(8);
        Font bigFont2 = textField2.getFont().deriveFont(Font.PLAIN, 40f);
        textField2.setFont(bigFont2);
        b1 = new JButton("SUBMIT");
        b1.setFont(new Font("Arial", Font.PLAIN, 40));
        newPanel = new JPanel(new GridLayout(2, 2, 20, 50));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel2 = new JPanel(new FlowLayout());
        newPanel2.add(b1);
        add(newPanel, BorderLayout.CENTER);
        add("South", newPanel2);
        b1.addActionListener(this);
        setTitle("ONLINE EXAMINATION LOGIN FORM");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (!passValue.equals(""))
            new OnlineTestBegin(userValue);
        else {
            textField2.setText("Enter Password : ");
            actionPerformed(ae);
        }
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log;
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer = new Timer();

    OnlineTestBegin(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save And Next");
        b1.setFont(new Font("Arial", Font.PLAIN, 20));
        b2 = new JButton("Save For Later");
        b2.setFont(new Font("Arial", Font.PLAIN, 20));
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        l.setBounds(70, 70, 800, 40);
        l1.setBounds(50, 30, 450, 40);
        jb[0].setBounds(90, 120, 500, 30);
        jb[1].setBounds(90, 160, 500, 30);
        jb[2].setBounds(90, 200, 500, 30);
        jb[3].setBounds(90, 240, 500, 30);
        b1.setBounds(150, 300, 200, 30);
        b2.setBounds(460, 300, 200, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(1000, 700);
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 600;

            public void run() {
                l1.setText("Time Left: " + i);
                l1.setFont(new Font("Arial", Font.PLAIN, 20));
                i--;
                if (i < 0) {
                    timer.cancel();
                    l1.setText("Time Out");
                    l1.setFont(new Font("Arial", Font.PLAIN, 20));
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (check()) {
                count = count + 1;
            }
            current++;
            set();
            if (current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
                b2.setFont(new Font("Arial", Font.PLAIN, 20));
            }
        }
        if (e.getActionCommand().equals("Save For Later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                b2.setText("Result");
            b2.setFont(new Font("Arial", Font.PLAIN, 20));
            setVisible(false);
            setVisible(true);
        }
        for (int i = 0, y = 1; i < x; i++, y++) {
            if (e.getActionCommand().equals("Review" + y)) {
                if (check())
                    count = count + 1;
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }
        }
        if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            current++;
            JOptionPane.showMessageDialog(this, "SCORE = " + count);
            System.exit(0);
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("1: Who invented Java Programming?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("Guido van Rossum");
            jb[1].setText("James Gosling");
            jb[2].setText("Dennis Ritchie");
            jb[3].setText("Bjarne Stroustrup");
            setFontForOptions();
        }
        if (current == 1) {
            l.setText("2: Out of these operators, which one can be used to get the run time info about an object?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText(" Info");
            jb[1].setText("getInfo");
            jb[2].setText("getinfoof");
            jb[3].setText("instanceof");
            setFontForOptions();
        }
        if (current == 2) {
            l.setText("3: Out of the following, which one is a superclass of all the exception type classes?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("String");
            jb[1].setText("RuntimeExceptions");
            jb[2].setText("Catchable");
            jb[3].setText("Throwable");
            setFontForOptions();
        }
        if (current == 3) {
            l.setText(
                    "4: Which of these components are used in a Java program for compilation, debugging, and execution?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("JDK");
            jb[1].setText("JVM");
            jb[2].setText("JRE");
            jb[3].setText("JIT");
            setFontForOptions();
        }
        if (current == 4) {
            l.setText(
                    "5: What is it called when the child object also gets killed when the parent object is killed in the program?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("Encapsulation");
            jb[1].setText("Association");
            jb[2].setText("Aggregation");
            jb[3].setText("Composition");
            setFontForOptions();
        }
        if (current == 5) {
            l.setText("6: The remover() method throws which of these exceptions:");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText(" ObjectNotFoundException");
            jb[1].setText(" IllegalStateException");
            jb[2].setText(" IOException");
            jb[3].setText("SystemException");
            setFontForOptions();
        }
        if (current == 6) {
            l.setText("7:Out of these methods, which one makes the raw MIME formatted string?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("toString()");
            jb[1].setText("getString()");
            jb[2].setText("parse()");
            jb[3].setText(" parseString()");
            setFontForOptions();
        }
        if (current == 7) {
            l.setText("8: Which of these packages contains the exception Stack Overflow in Java?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("java.io");
            jb[1].setText("java.system");
            jb[2].setText("java.lang");
            jb[3].setText("java.util");
            setFontForOptions();
        }
        if (current == 8) {
            l.setText("9: Out of these methods of the Object class, which one can clone an object?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("Object clone()");
            jb[1].setText(" clone()");
            jb[2].setText("Object copy()");
            jb[3].setText("copy()");
            setFontForOptions();
        }
        if (current == 9) {
            l.setText("10: Which one of the following is not an access modifier?");
            l.setFont(new Font("Arial", Font.PLAIN, 20));
            jb[0].setText("Protected");
            jb[1].setText("Void");
            jb[2].setText("Public");
            jb[3].setText("Private");
            setFontForOptions();
        }
        l.setBounds(70, 70, 800, 40);
        for (int i = 0, j = 0; i <= 130; i += 40, j++)
            jb[j].setBounds(90, 120 + i, 500, 30);
    }

    private void setFontForOptions() {
        for (int i = 0; i < 4; i++) {
            jb[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }

    boolean check() {
        if (current == 0)
            return (jb[1].isSelected());
        if (current == 1)
            return (jb[3].isSelected());
        if (current == 2)
            return (jb[3].isSelected());
        if (current == 3)
            return (jb[0].isSelected());
        if (current == 4)
            return (jb[3].isSelected());
        if (current == 5)
            return (jb[1].isSelected());
        if (current == 6)
            return (jb[2].isSelected());
        if (current == 7)
            return (jb[2].isSelected());
        if (current == 8)
            return (jb[0].isSelected());
        if (current == 9)
            return (jb[1].isSelected());
        return false;
    }
}

public class OnlineExamination {
    public static void main(String args[]) {
        try {
            Login form = new Login();
            form.setSize(1500, 800);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
