package Gui.passwortPruefung;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MyFrame extends JFrame implements ActionListener {

    private JLabel labelName;
    private JLabel labelAlter;
    private JLabel labelPasswort;
    private JLabel showCorrect;

    private JTextField eingabeName;
    private JTextField eingabeALter;
    private JPasswordField eingabePasswort;

    private JButton prüfen;

    private HashMap<String, String> speicher;

    private Timer timer;

    public MyFrame() {

        timer = new Timer(1000, this);

        speicher = new HashMap<>();
        speicher.put("Jakob", "Hallo");

        this.setSize(400, 400);
        setLayout(new GridLayout(4, 2));

        labelName = new JLabel("Name:");
        this.add(labelName);
        eingabeName = new JTextField();
        this.add(eingabeName);

        labelAlter = new JLabel("Alter:");
        this.add(labelAlter);
        eingabeALter = new JTextField();
        this.add(eingabeALter);

        labelPasswort = new JLabel("Passwort:");
        this.add(labelPasswort);
        eingabePasswort = new JPasswordField();
        this.add(eingabePasswort);

        prüfen = new JButton("prüfen");
        prüfen.addActionListener(this);
        this.add(prüfen);
        showCorrect = new JLabel("Daten Eigeben");
        this.add(showCorrect);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prüfen) {
            if (check(eingabeName.getText(), eingabeALter.getText(), eingabePasswort.getPassword())) {
                showCorrect.setText("Korrekt");
            } else {
                showCorrect.setText("Falsch");
                timer.start();
            }
        } else if (e.getSource() == timer) {
            showCorrect.setText("Daten Eingeben");
        }
    }

    private boolean check(String name, String alterText, char[] password) {
        int alter = 0;
        if (!alterText.isEmpty())
            try {
                alter = Integer.valueOf(alterText);
            } catch (Exception e) {
                return false;
            }
        if (name.isEmpty() || password.length == 0) {
            return false;
        }
        try {
            if (alter > 17) {
                if (speicher.get(name).equals(new String(password))) {
                    return true;
                }
            }
        } catch (Exception exception) {

        }
        return false;
    }

}
