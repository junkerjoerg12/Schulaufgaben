package UebungLN;

public class Motor extends Einzelteil{
    
    private int maxRpm;
    private Zylinder zylinder[];

    public Motor(int anzahlZylinder, int modellnummer){
        super(modellnummer);
        zylinder= new Zylinder[anzahlZylinder];

        for(int i= 0; i<zylinder.length; i++){
            zylinder[i]= new Zylinder(30, i);
        }
    }

    public int getMaxRpm(){
        return maxRpm;
    }

}
