package vererbung;

public class AutoKinoTicket extends KinoTicket{

    private int anzahlPersonen;

    public AutoKinoTicket(String ort, String name, int basispreis, int filmleange, int anzahlPersonen) {
        super(ort, name, basispreis, filmleange);
        this.anzahlPersonen=anzahlPersonen;
    }


    
    public double berechneTicketPreis(){
        ticketpreis=super.berechneTicketPreis()*anzahlPersonen;
        return ticketpreis;
    }

    @Override
    public void printTicketdaten() {
        System.out.println("Veranstaltungsort: "+ veranstaltungsort);
        System.out.println("Eventname: "+eventname) ;
        System.out.println("Filmlänge: "+ filmlaenge);
        System.out.println("AnzahlPersonen"+ anzahlPersonen);
        
    }

}
