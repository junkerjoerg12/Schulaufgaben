package Exceptions.exceptions0a;

public class Konto {
  private Person inhaber;
  private double guthaben;

  public Konto(Person inhaber) {
    this.inhaber = inhaber;
  }

  public Konto() {

  }

  public Person getInhaber() {
    return inhaber;
  }

  public double getGuthaben() {
    return guthaben;
  }

  public void einzahlen(double betrag) {
    guthaben += betrag;
  }

  public void abheben(double betrag) throws Exception {
    if (guthaben - betrag < 0) {
      throw new Exception("So viel Geld ist nicht mehr da");
    } else {
      guthaben -= betrag;
    }
  }

  public String toString() {
    try {
      return inhaber.toString() + " " + guthaben;
    } catch (NullPointerException e) {
      return "Kein Inhaber gefunden";
    }
  }
}
