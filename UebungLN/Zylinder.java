package UebungLN;

public class Zylinder{

    private int durchmesser;
    private int modellnummer;

    public Zylinder(int durchmesser, int modellnummer){
        this.durchmesser= durchmesser;
        this.modellnummer= modellnummer;
    }

    public int getModellnummer(){
        return modellnummer;
    }

    public int getDurchmesser(){
        return durchmesser;
    }
}
