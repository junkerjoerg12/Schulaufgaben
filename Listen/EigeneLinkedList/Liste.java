public class Liste {

    private ListenObject start;
    private int anzahlObjekte = 0;

    public Liste() {

    }

    public Liste(ListenObject start) {
        this.start = start;
    }

    public boolean remove(int index) {
        anzahlObjekte--;
        return true;
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

    public void print() {
        if (start == null) {
            System.out.println("No objects found in this list");
        } else {
            start.print();
        }
        System.out.println("\n");
    }

}