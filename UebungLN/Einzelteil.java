package UebungLN;

public abstract class Einzelteil {
    
    protected int modellnummer;

    public Einzelteil(int modellnummer){
        this.modellnummer = modellnummer;
    }


    public int getModellnummer(){
        return modellnummer;
    }
}
