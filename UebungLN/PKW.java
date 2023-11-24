package UebungLN;

public class PKW {
    
    private Fahrzeughalter fahrzeughalter;
    private String fahrzeugmarke;
    private Einzelteil[] enthaelt= new Einzelteil[100];

    
    public PKW(Fahrzeughalter fahrzeughalter, String fahrzeugmarke){
        this.fahrzeughalter= fahrzeughalter;
        this.fahrzeugmarke= fahrzeugmarke;
    }

    public Fahrzeughalter getFahrzeughalter() {
        return this.fahrzeughalter;
    }


    public String getFahrzeugmarke() {
        return this.fahrzeugmarke;
    }

    public Einzelteil[] getEnthaelt() {
        return this.enthaelt;
    }


}
