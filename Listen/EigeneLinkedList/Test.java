/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Liste liste = new Liste();
        Liste liste2 = new Liste();
        Liste liste3 = new Liste();

        liste.add(new TestObjekt(1));
        liste.add(new TestObjekt(2));
        liste.add(new TestObjekt(3));
        liste.add(new TestObjekt(4));
        liste.add(new TestObjekt(5));
        liste.add(new TestObjekt(6));
        liste.add(new TestObjekt(7));
        liste.add(new TestObjekt(8));
        liste.add(new TestObjekt(9));
        liste.print();
        liste.add(new TestObjekt(10), 2);
        // liste.print();
        // liste.add(new TestObjekt(1), 11);
        // liste.print();
        // liste.add(new TestObjekt(12), 0);
        liste.print();

        System.out.println("\n");
        liste.print(8);
        System.out.println("\n");

        liste.remove(8);
        liste.print();

        liste2.add(new TestObjekt(100));
        liste2.add(new TestObjekt(101));
        liste2.add(new TestObjekt(102));
        liste2.add(new TestObjekt(103));

        liste.print();

        liste3.add(new TestObjekt(199));
        liste3.add(new TestObjekt(198));
        liste3.add(new TestObjekt(197));

        liste.add(liste3, 0);
        liste.print();

    }
}