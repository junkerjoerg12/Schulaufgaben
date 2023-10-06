package vererbung;

public class Sportticket extends Ticket{
    private int pokalstufe;


    
    public Sportticket(String ort, String name, int basispreis, int pokalstufe) {
        super(ort, name, basispreis);
        this.pokalstufe= pokalstufe;
        //TODO Auto-generated constructor stub
    }

    @Override
    public double berechnePreis() {
        ticketpreis=basispreis+10*pokalstufe; 
        return ticketpreis;
    }
    
}
