public class ListenObject {

    private ListenObject vorgaenger;
    private ListenObject nachfolger;
    private Object ptrTo;

    public ListenObject(ListenObject vorgaenger, ListenObject nachfolger, Object ptrTo) {
        this.nachfolger = nachfolger;
        this.vorgaenger = vorgaenger;
        this.ptrTo = ptrTo;
    }

    public ListenObject(ListenObject vorgaenger, Object ptrTo) {
        this.vorgaenger = vorgaenger;
        this.ptrTo = ptrTo;
    }

    public ListenObject(Object ptrTo) {
        this.ptrTo = ptrTo;
    }

    public void remove(int index) {
        // wenn das Gesuchte Element gefunden wurde werden nachfoger und vorgänger
        // verbunden
        if (index == 0) {
            if (nachfolger == null) {
                vorgaenger.setNachfolger(null);
            } else {
                vorgaenger.setNachfolger(nachfolger);
                nachfolger.setVorgaenger(vorgaenger);
            }

            // wenn nach letztem Element in der Liste das Gesuchte nicht gefunden wurde
            // Fehlermeldung ausgeben
        } else if (nachfolger == null) {
            System.out.println("Kein Objekt an der Stelle Gefunden");

            // nächstes Element überprüfen
        } else {
            nachfolger.remove(--index);
        }
    }

    // fügt neues Element am Ende der Liste hinzu
    public boolean add(Object o) {
        // wenn das Letzte Element gefudnen wurde wird das Neue Initialisiert
        if (nachfolger == null) {
            nachfolger = new ListenObject(this, o);
        } else {
            // sonst methode im nächsten Element aufrufen
            nachfolger.add(o);
        }
        return true;
    }

    // neues Element wird an bestimmter Stelle eingefügt
    public boolean add(Object o, int index) {
        // wenn die Gesuchte Stelle gefunden wurde
        if (index == 0) {
            // Nachfolger des Objekts wird zwischengespeichert
            ListenObject tempNachfolger;
            tempNachfolger = nachfolger;

            // dem neu erstellten Objekt wird this als vorgänger und der
            // Zwischengespeicherte nachfolger als nachfolger übergeben
            nachfolger = new ListenObject(this, tempNachfolger, o);

            //
            // nachfolger.setNachfolger(tempNachfolger);

            // sonst funktion im folgeogjekt aufrufen
        } else {
            nachfolger.add(o, --index);
        }

        return true;
    }

    // public ListenObject loopTo(int toGo) {
    // if (toGo == 0) {
    // return this;
    // } else if (nachfolger != null) {
    // return nachfolger.loopTo(toGo - 1);
    // } else {
    // System.out.println("Kein Objekt an der Stelle gefunden");
    // return null;
    // }
    // }

    public ListenObject getLast() {
        // wenn this das letzte Element der Liste ist wird es zurückgegeben
        if (nachfolger == null) {
            return this;

            // sonst funktion im folgeobjekt aufrufen
        } else
            return nachfolger.getLast();
    }

    // gibt die gesamte Liste aus
    public void print() {
        // printet das referenzierte Objekt
        System.out.println(ptrTo);
        // und ruft die Methode im folgeobjekt auf, fall das existiert
        if (nachfolger != null) {
            nachfolger.print();
        }
    }

    // gibt das Objekt an einer Bestimmten stelle aus
    public void print(int index) {
        if (index == 0) {
            System.out.println(ptrTo);
        } else {
            nachfolger.print(--index);
        }
    }

    public Object get(int index) {
        if (index == 0) {
            return ptrTo;
        } else if (nachfolger != null) {
            return nachfolger.get(--index);
        } else {
            return null;
        }

    }

    public void setNachfolger(ListenObject o) {
        nachfolger = o;
    }

    public ListenObject getNachfolger() {
        return nachfolger;
    }

    public void setVorgaenger(ListenObject o) {
        vorgaenger = o;
    }
}
