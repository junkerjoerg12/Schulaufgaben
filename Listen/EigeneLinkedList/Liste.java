
public class Liste {

    private Object start;
    private int anzahlObjekte = 0;

    public Liste() {

    }

    public Liste(Object start) {

        this.start = start;
    }

    public boolean remove(int index) {

        anzahlObjekte--;
        return true;
    }

    public boolean add(int index) {

        anzahlObjekte++;
        return true;
    }

    public boolean add() {

        anzahlObjekte++;
        return true;
    }

}
