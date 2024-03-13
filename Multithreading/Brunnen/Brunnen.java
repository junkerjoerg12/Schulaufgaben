package Multithreading.Brunnen;

public class Brunnen {
  private int maxFuell;
  private int fuellmenge;

  public Brunnen(int maxFuell) {
    this.maxFuell = maxFuell;
  }

  public synchronized void entnehmen(int menge) {
    if (!(menge > fuellmenge)) {
      fuellmenge -= menge;
      System.out.println("entnommene Menge: " + menge);
      System.out.println("Aktueller Füllstand: " + fuellmenge);
      try {
        Thread.sleep(menge * 200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println(menge + " konne nicht entnommen werden! Es befinden sich weiterhin " + fuellmenge
          + " Liter wasser im System");
    }
  }

  public synchronized void hinzufügen(int menge) {
    if (fuellmenge + menge <= maxFuell) {
      fuellmenge += menge;
      System.out.println("Hinzugefügte Menge: " + menge);
      System.out.println("Aktueller Füllstand: " + fuellmenge);
      try {
        Thread.sleep(menge * 200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println(menge + " konne nicht hinzugefügt werden! Es befinden sich weiterhin " + fuellmenge
          + " Liter wasser im System");
    }
  }
}
