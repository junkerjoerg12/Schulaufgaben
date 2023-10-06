package vererbung;

public class Kinoticket extends Ticket{

    private int filmlaenge;

    public Kinoticket(String ort, String name, int basispreis, int filmleange) {
        super(ort, name, basispreis);
        this.filmlaenge= filmleange;
    }

    @Override
    public double berechnePreis() {
        if(filmlaenge>150){
            ticketpreis= basispreis+3;
        }
        return ticketpreis;
    }
    
}
