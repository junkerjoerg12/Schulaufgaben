package vererbung;

public class KinoTicket extends Ticket {

    protected int filmlaenge;

    public KinoTicket(String ort, String name, int basispreis, int filmleange) {
        super(ort, name, basispreis);
        this.filmlaenge = filmleange;
    }

    @Override
    public double berechneTicketPreis() {
        ticketpreis = basispreis;
        if (filmlaenge > 150) {
            ticketpreis = basispreis + 3;
        }
        return ticketpreis;
    }

    @Override
    public void printTicketdaten() {
        System.out.println("Veranstaltungsort: " + veranstaltungsort);
        System.out.println("Eventname: " + eventname);
        System.out.println("Filmlänge: " + filmlaenge);

    }

}
