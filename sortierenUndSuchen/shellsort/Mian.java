package shellsort;

import java.util.ArrayList;
import java.util.Random;

import insertionSort.insertionSort;

public class Mian {
  public static void main(String[] args) {
    int[] arr = new int[100];
    Random rand = new Random();

    for (int i = 0; i < arr.length; i++) {
      arr[i] = rand.nextInt(100);
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println("\n");
    arr = shellsort(arr);
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  private static int[] shellsort(int[] arr) {
    int distanz = 1;
    int length = arr.length;
    while (distanz < length) {
      distanz = 3 * distanz + 1;
    }

    while (distanz >= 1) {
      System.err.println("h = " + distanz);
      distanz = (distanz - 1) / 3;
      for (int f = 0; f < distanz; f++) {

        int momentanesElement = f + distanz;

        while (momentanesElement < length) {

          int temp = arr[momentanesElement];
          int einfuegeposition = momentanesElement - distanz;

          while (einfuegeposition > f && arr[einfuegeposition] > temp) {
            arr[einfuegeposition + distanz] = arr[einfuegeposition];
            einfuegeposition -= distanz;

          }
          arr[einfuegeposition] = temp;
          momentanesElement += distanz;
        }
      }
    }
    return arr;
  }

}
