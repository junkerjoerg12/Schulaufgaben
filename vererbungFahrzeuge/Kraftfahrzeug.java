package vererbungFahrzeuge;

public class Kraftfahrzeug extends Fahrzeug{
    private double hoechstgeschwindigkeit;
    private double leistung;

    public void druckefahrzeugdaten(){
        System.out.println(hoechstgeschwindigkeit);
        System.out.println(leistung);
    }
}
