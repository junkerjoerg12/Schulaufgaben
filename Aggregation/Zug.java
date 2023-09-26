package Aggregation;

public class Zug {
    private int anzahl;
    private int  maxZahl=12;
    private Waggon[] waggons= new Waggon[maxZahl];

    public Zug(){
        for(int i=0; i<maxZahl; i++){
            waggons[i]= null;
        }
    }

        

    public int sucheMaxZiel(){
        int maxZiel=0;

        for(int i=0; i<waggons.length; i++){
            if(waggons[i].getZiel()>maxZiel){
                maxZiel= waggons[i].getZiel();
            }
        }
        return maxZiel;
    }

    public void anKuppeln(Waggon einWaggon){
        for(int i=0; i<waggons.length; i++){
            if(waggons[i]==null){
                waggons[i]= einWaggon;
                return;
            }else{
                System.out.println("Der Zug int bereits voll!!!");
            }
        }
    }


    public Waggon abkoppeln(){
        Waggon abzukoppelnderWaggon= null;

        for(int i=0; i<waggons.length; i++){
            if(waggons[i]==null){
                abzukoppelnderWaggon= waggons[i-1];
                waggons[i-1]= null;
            }
            if(i==maxZahl-1&&waggons[i]!=null){
                abzukoppelnderWaggon= waggons[i];
                waggons[i]=null;
            }
        }

        return abzukoppelnderWaggon;
    }

    public int getAnzahl(){
        return anzahl;
    }
}
