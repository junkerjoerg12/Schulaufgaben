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

    public ListenObject getNext() {
        return nachfolger;
    }

    public ListenObject getPrevious() {
        return vorgaenger;
    }

    public boolean add(Object o) {
        if (nachfolger == null) {
            nachfolger = new ListenObject(o);
        } else {
            nachfolger.add(o);
        }
        return true;
    }

    public boolean add(Object o, int index) {
        if (index == 0) {
            ListenObject tempNachfolger;
            ListenObject tempVorgaenger;
            tempNachfolger = nachfolger;
            tempVorgaenger = vorgaenger;
            nachfolger = new ListenObject(o);
            nachfolger.setNachfolger(tempNachfolger);
        } else {
            nachfolger.add(o, --index);
        }

        return true;
    }

    public ListenObject loopTo(int toGo) {
        if (toGo == 0) {
            return this;
        } else if (nachfolger != null) {
            return nachfolger.loopTo(toGo - 1);
        } else {
            System.out.println("Kein Objekt an der Stelle gefunden");
            return null;
        }
    }

    public void print() {
        System.out.println(ptrTo);
        if (nachfolger != null) {
            nachfolger.print();
        }
    }

    public void setNachfolger(ListenObject o) {
        nachfolger = o;
    }

}
