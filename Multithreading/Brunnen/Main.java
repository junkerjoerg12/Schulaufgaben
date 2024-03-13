package Multithreading.Brunnen;

public class Main {
  public static void main(String[] args) {

    Brunnen b = new Brunnen(500);

    Pumpe p2 = new Pumpe(b);

    Einlauf e1 = new Einlauf(b);

    e1.start();
    System.out.println("E1 gestarte, pumpen methode wird aufgerufen");

    System.out.println(" p gestartet!!!");
    System.out.println();

    p2.start();
  }

}
