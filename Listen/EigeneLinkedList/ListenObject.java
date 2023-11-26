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

    public void remove(int index) {
        if (index == 0) {
            if (nachfolger == null) {
                vorgaenger.setNachfolger(null);
            } else {
                vorgaenger.setNachfolger(nachfolger);
                nachfolger.setVorgaenger(vorgaenger);
            }
        } else if (nachfolger == null) {
            System.out.println("Kein Objekt an der Stelle Gefunden");
        } else {
            nachfolger.remove(--index);
        }
    }

    public boolean add(Object o) {
        if (nachfolger == null) {
            nachfolger = new ListenObject(this, o);
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
            nachfolger = new ListenObject(tempVorgaenger, tempNachfolger, o);
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

    public ListenObject getLast() {
        if (nachfolger == null) {
            return this;
        } else
            return nachfolger.getLast();
    }

    public void print() {
        System.out.println(ptrTo);
        if (nachfolger != null) {
            nachfolger.print();
        }
    }

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
        } else {
            return nachfolger.get(--index);
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
