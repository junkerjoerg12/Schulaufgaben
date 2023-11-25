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

    public boolean add(int index, Object o) {
        anzahlObjekte++;
        return true;
    }

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
            System.out.println("No objects foudn in this list");
        } else {
            start.print();
        }
    }

}