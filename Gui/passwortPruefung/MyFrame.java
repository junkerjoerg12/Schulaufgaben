package Gui.passwortPruefung;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MyFrame extends JFrame {

  private JLabel labelName;
  private JLabel labelAlter;
  private JLabel labelPasswort;
  private JLabel showCorrect;

  private JTextField eingabeName;
  private JTextField eingabeALter;
  private JPasswordField eingabePasswort;

  private JButton prüfen;

  public MyFrame() {
    setLayout(new GridLayout(4, 2));
    setVisible(true);
    labelAlter = new JLabel("Name:");
    labelAlter = new JLabel("Alter:");
    labelPasswort = new JLabel("Passwort:");

  }

  public static void main(String[] args) {
    MyFrame f = new MyFrame();

  }

}
