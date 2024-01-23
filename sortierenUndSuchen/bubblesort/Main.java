
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    int[] zahlen = new int[500000];
    Random rand = new Random();

    for (int i = 0; i < zahlen.length; i++) {
      zahlen[i] = rand.nextInt(10000);
    }

    // for (int i = 0; i < zahlen.length; i++) {
    // System.out.println(zahlen[i]);
    // }
    System.out.println("\n");
    long start = System.currentTimeMillis();
    zahlen = bubblesort(zahlen);
    System.out.println(
        "Sortiert " + zahlen.length + " zahlen in " + (System.currentTimeMillis() - start) + " Millisekunden");
    // for (int i = 0; i < zahlen.length; i++) {
    // System.out.println(zahlen[i]);
    // }

  }

  public static int[] bubblesort(int[] zahlen) {
    // int counter = 0;
    for (int i = 0; i < zahlen.length; i++) {
      for (int j = 0; j < zahlen.length - i - 1; j++) {
        if (zahlen[j] > zahlen[j + 1]) {
          int temp = zahlen[j + 1];
          zahlen[j + 1] = zahlen[j];
          zahlen[j] = temp;
        }
        // counter++;
      }
    }
    // System.out.println("Anzahl der Vergleiche: " + counter);
    return zahlen;
  }
}
