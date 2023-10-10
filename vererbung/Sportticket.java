package vererbung;

public class SportTicket extends Ticket{
    private int pokalstufe;


    
    public SportTicket(String ort, String name, int basispreis, int pokalstufe) {
        super(ort, name, basispreis);
        this.pokalstufe= pokalstufe;
    }

    @Override
    public double berechneTicketPreis() {
        ticketpreis=basispreis+10*pokalstufe; 
        return ticketpreis;
    }

    @Override
    public void printTicketdaten() {
        System.out.println("Veranstaltungsort: "+ veranstaltungsort);
        System.out.println("Eventname: "+eventname) ;
        System.out.println("Pokalstufe: "+ pokalstufe);
        
    }

    
    
}
