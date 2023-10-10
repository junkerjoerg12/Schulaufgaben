package UebungLN;

public class Fahrzeughalter {
    private String name;
    private PKW pkw;


    public Fahrzeughalter(String name){
        this.name= name;
        pkw= new PKW(this);
    }
}
