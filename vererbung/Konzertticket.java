package vererbung;

public class KonzertTicket extends Ticket{

    private int sitzreihe;

    public KonzertTicket(String ort, String name, int basispreis, int sitzreihe) {
        super(ort, name, basispreis);
        this.sitzreihe= sitzreihe;
    }

    @Override
    public double berechneTicketPreis() {
        ticketpreis= basispreis*(1+1/sitzreihe);
        return ticketpreis;
    }

    @Override
    public void printTicketdaten() {
        System.out.println("Veranstaltungsort: "+ veranstaltungsort);
        System.out.println("Eventname: "+eventname) ;
        System.out.println("Sitzreihe: "+ sitzreihe);
        
        
    }
    
}
