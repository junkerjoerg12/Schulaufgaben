package vererbung;

public abstract class Ticket {
    protected double basispreis;
    protected double ticketpreis;
    protected String eventname;
    protected String veranstaltungsort;

    public Ticket(String ort, String name, int basispreis){
        this.veranstaltungsort= ort; 
        this.eventname= name; 
        this.basispreis= basispreis;

    }

    public abstract double berechneTicketPreis();

    public abstract void printTicketdaten();             
}
