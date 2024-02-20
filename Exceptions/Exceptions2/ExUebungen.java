package Exceptions.Exceptions2;

import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ExUebungen {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    frame.setSize(500, 500);
    frame.add(new JLabel(getZufallsZahl() + ""));
  }

  private static double getZufallsZahl() {
    Random random = new Random();
    try {
      return random.nextDouble(0.0, 0.5);
    } catch (Exception e) {
      return 0.5;
    }
  }
}
