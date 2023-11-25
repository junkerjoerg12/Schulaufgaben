public abstract class ListenObjectSuper {

    protected ListenObjectSuper vorgaenger;
    protected ListenObjectSuper nachfolger;

    public ListenObjectSuper getNext() {

        return nachfolger;
    }

    public ListenObjectSuper getPrevious() {

        return vorgaenger;
    }

    public ListenObjectSuper loopTo(int toGo) {
        if (toGo == 0) {
            return this;
        } else if (nachfolger != null) {
            return nachfolger.loopTo(toGo - 1);
        } else {
            System.out.println("Kein Objekt an der Stelle gefunden");
            return null;
        }

    }

}
