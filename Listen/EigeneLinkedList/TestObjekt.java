public class TestObjekt {

    private int inhalt;

    public TestObjekt(int inhalt) {
        this.inhalt = inhalt;
    }

    public int getInhalt() {
        return inhalt;
    }

    public String toString() {
        String inhalt = "";
        inhalt += this.inhalt;
        return inhalt;
    }
}
