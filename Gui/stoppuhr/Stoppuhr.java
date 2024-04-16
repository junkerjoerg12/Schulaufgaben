package Gui.stoppuhr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Stoppuhr extends JFrame implements ActionListener {

    private JLabel startzeit;
    private JLabel stoppzeit;
    private JLabel laufzeit;

    public Stoppuhr() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new Stoppuhr();
    }

}
