package vererbung;

public class Konzertticket extends Ticket{

    private int sitzreihe;

    public Konzertticket(String ort, String name, int basispreis, int sitzreihe) {
        super(ort, name, basispreis);
        this.sitzreihe= sitzreihe;
    }

    @Override
    public double berechnePreis() {
        ticketpreis= basispreis*(1+1/sitzreihe);
        return ticketpreis;
    }
    
}
