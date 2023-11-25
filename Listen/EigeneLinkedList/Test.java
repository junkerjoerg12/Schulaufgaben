/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Liste liste = new Liste();
        liste.add(new TestObjekt(42));
        liste.add(new TestObjekt(1));
        liste.add(new TestObjekt(3));
        liste.add(new TestObjekt(5));
        liste.add(new TestObjekt(6));
        liste.add(new TestObjekt(7));
        liste.add(new TestObjekt(7));
        liste.add(new TestObjekt(8));
        liste.add(new TestObjekt(12));
        liste.print();
        liste.add(new TestObjekt(13), 2);
        liste.print();
        liste.add(new TestObjekt(420), 11);
        liste.print();
        liste.add(new TestObjekt(0), 0);
        liste.print();

    }
}