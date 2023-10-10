package vererbungFahrzeuge;

public class Fahrzeug {
    private String fahrzugnummer;
    private double leergewicht;
    private double gesamtgewicht;

    public void druckefahrzeugdaten(){
        System.out.println(fahrzugnummer);
        System.out.println(leergewicht);
        System.out.println(gesamtgewicht);
    }
}
