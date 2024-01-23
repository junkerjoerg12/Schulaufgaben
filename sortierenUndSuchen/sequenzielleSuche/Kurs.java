package sequenzielleSuche;

import java.util.ArrayList;

public class Kurs {
  ArrayList<Schueler> schueler = new ArrayList<Schueler>();

  public void schuelerHinzufuegen(Schueler schueler) {
    this.schueler.add(schueler);
  }

  public int sucheseq(String nachname) {
    for (int i = 0; i < schueler.size(); i++) {
      if (schueler.get(i).getNachname().equals(nachname)) {
        return i;
      }
    }
    return -1;
  }
}
