package Gui.test;

import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

  public MyFrame() {
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);
    getContentPane().setBackground(Color.BLACK);
  }
}
