package Multithreading.Brunnen;

import java.util.Random;

public class Einlauf extends Thread {
  private Brunnen brunnen;

  public Einlauf(Brunnen brunnen) {
    this.brunnen = brunnen;
  }

  public void run() {
    Random rand = new Random();
    while (true) {
      brunnen.hinzufügen(rand.nextInt(50));
    }

  }
}
