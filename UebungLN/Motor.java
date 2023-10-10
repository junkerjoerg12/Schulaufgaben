package UebungLN;

public class Motor extends Einzelteil{
    
    private int rpm;
    private Zylinder zylinder[];

    public Motor(int anzahlZylinder){
        zylinder= new Zylinder[anzahlZylinder];

        for(int i= 0; i<zylinder.length; i++){
            zylinder[i]= new Zylinder();
        }
    }
}
