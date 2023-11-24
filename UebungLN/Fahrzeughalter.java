package UebungLN;

public class Fahrzeughalter {
    private String name;
    private PKW pkw;

    public Fahrzeughalter(String name, String automarke){
        this.name= name;
        pkw= new PKW(this, automarke);
    }
    
    
    public String getName() {
        return this.name;
    }

    public PKW getPkw() {
        return this.pkw;
    }
    
}
