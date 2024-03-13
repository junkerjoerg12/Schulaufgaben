package Multithreading.Brunnen;

import java.util.Random;

public class Pumpe extends Thread {

  private Brunnen brunnen;

  public Pumpe(Brunnen brunnen) {
    this.brunnen = brunnen;
  }

  public void run() {
    Random rand = new Random();
    while (true) {
      brunnen.entnehmen(rand.nextInt(50));
    }

  }
}
