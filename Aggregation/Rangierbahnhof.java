package Aggregation;

public class Rangierbahnhof {
    private Zug Zug0;
    private Zug Zug1;
    private Zug Zug2;


    public void waggonAbstossn(){
        Waggon einWaggon;
        int maxZiel= Zug0.sucheMaxZiel();
        int anzahlwaggons= Zug0.getAnzahl();
        for(int i=0; i<anzahlwaggons;i++){
            einWaggon= Zug0.abkoppeln();
        }

    }
}
