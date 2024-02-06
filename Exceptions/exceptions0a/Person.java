package Exceptions.exceptions0a;

import java.util.ArrayList;

public class Person {
  private String vorname;
  private String zuname;
  private ArrayList<String> addresse = new ArrayList<>();

  public String getVorname() {
    return vorname;
  }

  public void setVorname(String vorname) {
    this.vorname = vorname;
  }

  public String getZuname() {
    return zuname;
  }

  public void setZuname(String zuname) {
    this.zuname = zuname;
  }

  public void setAddresse(String addresse) {
    this.addresse.add(addresse);
  }

  public String toString() {
    return vorname + " " + zuname;
  }
}
