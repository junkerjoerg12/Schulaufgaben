package schuelerdaten;

import java.util.ArrayList;

public class Klasse {
    private String name; 
    private int anzahlAktuell;
    private String tutor;
    private ArrayList<Schueler>enthaelt = new ArrayList<Schueler>(); 

    public Klasse(String name, String tutor){
        this.name= name; 
        this.tutor= tutor;
    }

    public void schuelerHinzufuegen(Schueler schueler){
        if(!(enthaelt.size()<32)){
            enthaelt.add(schueler);
        }
    }
}
