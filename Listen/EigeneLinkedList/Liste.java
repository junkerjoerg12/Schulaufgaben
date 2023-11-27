public class Liste {

    private ListenObject start;
    private int anzahlObjekte = 0;

    public Liste() {

    }

    public Liste(ListenObject start) {
        this.start = start;
    }

    // Löscht das Objekt an übergebener Stelle aus der Liste
    public boolean remove(int index) {
        // if (index == 0) {
        // start = start.getNachfolger();
        // } else {
        // start.remove(index);
        // }
        start.remove(index);
        anzahlObjekte--;
        return true;
    }

    public void remove(Object o) {

    }

    // fügt das übergebene Objekt an der übergebenen Stelle ein, staret bei 0
    public boolean add(Object o, int index) {
        // wenn der angegeben Index größer als die List ist wird das Objekt an letzter
        // stelle hinzugefügt
        if (index >= (anzahlObjekte++)) {
            add(o);
        } else {
            if (index == 0) {
                ListenObject temp;
                temp = start;
                start = new ListenObject(o);
                start.setNachfolger(temp);
            } else {
                start.add(o, --index);
            }
            anzahlObjekte++;
        }
        return true;
    }

    // fügt die Übergebene Liste an das Ende der Liste
    public boolean add(Liste l) {
        // Erstes Objekt der Übergebenen Liste wird zum Nahcfolger dieser Liste
        getLast().setNachfolger(l.start);
        // Letstes Objekt dieser Liste wird zum Vorgengär des ersten objekts
        // der übergebenen Liste
        l.start.setVorgaenger(getLast().getNachfolger());
        // gesamtanzahl der enthaltenen Objekte wird berechnet
        anzahlObjekte += l.getAnzahlObjekte();
        return true;
    }

    public boolean add(Liste l, int index) {
        start.add(l, index);
        return true;
    }

    // fügt das übegebene Objekt an der Letzten stelle hinzu
    public boolean add(Object o) {
        if (start == null) {
            start = new ListenObject(o);
        } else {
            start.add(o);
        }
        anzahlObjekte++;
        return true;
    }

    public ListenObject getLast() {
        if (start != null) {
            return start.getLast();
        } else {
            return null;
        }
    }

    // Printet die gesamte Liste
    public void print() {
        if (start == null) {
            System.out.println("No objects found in this list");
        } else {
            start.print();
        }
        System.out.println("\n");
    }

    // printet das Element an der Übergebenen Stelle
    public void print(int index) {
        start.print(index);
    }

    // gibt das Objekt an übergeber Stelle zurück
    public Object get(int index) {
        return start.get(index);
    }

    public int getAnzahlObjekte() {
        return anzahlObjekte;
    }

    public ListenObject getStart() {
        return start;
    }

}