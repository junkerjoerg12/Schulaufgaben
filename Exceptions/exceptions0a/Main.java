package Exceptions.exceptions0a;

public class Main {

  public static void main(String[] args) {
    Person x = new Person();
    x.setVorname("Jakob");
    // x.setZuname("Engel");

    Konto konto = new Konto(x);
    konto.einzahlen(50000);
    try {
      konto.abheben(2000);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      konto.abheben(2093);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      System.out.println(konto.toString());
    } catch (NullPointerException e) {
      e.printStackTrace();
    }
  }
}
