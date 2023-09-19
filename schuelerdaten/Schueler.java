package schuelerdaten;

import java.util.ArrayList;
import java.util.Random;

public class Schueler {
    private int sNr;
    private String vorname;
    private String nachname;
    private Klasse klasse;
    private static int anzahl;
    private ArrayList<Kurs> kursListe = new ArrayList<>();
    private Random rand = new Random();

    public Schueler(String vorname, String nachname, Klasse klasse){
        this.vorname = vorname;
        this.nachname = nachname;
        this.klasse = klasse;
        klasse.hinzufuegen(this);
        genarateSNr();
        anzahl++;
    }

    private void genarateSNr(){
        sNr = vorname.substring(0, 1);
        sNr = sNr + nachname.substring(0, 1);
        sNr = sNr + rand.nextInt(100000000);
        this.sNr = sNr;
    }

    public String getsNr() {
        return sNr;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public Klasse getKlasse() {
        return klasse;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setKlasse(Klasse klasse) {
        this.klasse = klasse;
    }

    public ArrayList<Kurs> getKursListe() {
        return kursListe;
    }
}
