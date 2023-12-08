package bubblesort;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    int[] zahlen = new int[40000];
    Random rand = new Random();

    for (int i = 0; i < zahlen.length; i++) {
      zahlen[i] = rand.nextInt(100000);
    }

    for (int i = 0; i < zahlen.length; i++) {
      System.out.println(zahlen[i]);
    }
    System.out.println("\n");
    zahlen = bubblesort(zahlen);
    for (int i = 0; i < zahlen.length; i++) {
      System.out.println(zahlen[i]);
    }

  }

  public static int[] bubblesort(int[] zahlen) {
    int counter = 0;
    for (int i = 0; i < zahlen.length; i++) {
      for (int j = 0; j < zahlen.length - i - 1; j++) {
        if (zahlen[j] > zahlen[j + 1]) {
          int temp = zahlen[j + 1];
          zahlen[j + 1] = zahlen[j];
          zahlen[j] = temp;
        }
        counter++;
      }
    }
    System.out.println("Anzahl der Vergleiche: " + counter);
    return zahlen;
  }
}
