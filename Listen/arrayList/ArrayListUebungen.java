package arrayList;

import java.util.ArrayList;
import java.util.Arrays;

class ArrayListUebungen {

    public static void main(String[] args) {
        ArrayList<String> a = new ArrayList<String>();

        a.add("Eva");
        a.add(0, "Charisma");
        a.add("Pallas");

        String c[] = new String[] { "Tina", "Wilhelmine" };

        ArrayList<String> b = new ArrayList<String>(Arrays.asList(c));
        System.out.println(b);
        a.addAll(3, b);
        System.out.println(a);

        a.add("XXX");
        a.set(4, "Eva");
        System.out.println(a);
        System.out.println(a.size());

        System.out.println(a.contains("Tina"));

        System.out.println(a.contains("Tina") && a.contains("Wilhelmine"));

    }

}