package UebungLN;

public class PKW {
    
    private Fahrzeughalter fahrzeughalter;
    private String fahrzeugmarke;
    private Einzelteil[] enthaelt= new Einzelteil[100];

    public PKW(Fahrzeughalter fahrzeughalter, String fahrzeugmarke){
        this.fahrzeughalter= fahrzeughalter;
        this.fahrzeugmarke= fahrzeugmarke;
    }
}
