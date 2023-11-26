/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        Liste liste = new Liste();

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
        // liste.add(new TestObjekt(10), 2);
        // liste.print();
        // liste.add(new TestObjekt(1), 11);
        // liste.print();
        // liste.add(new TestObjekt(12), 0);
        // liste.print();

        System.out.println("\n");
        liste.print(8);
        System.out.println("\n");

        liste.remove(8);
        liste.print();

        System.out.println(liste.get(0));
        System.out.println(liste.get(1));
        System.out.println(liste.get(2));

    }
}