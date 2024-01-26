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
    System.err.println("sortiert: ");
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
  }

  private static int[] shellsort(int[] arr) {
    // int distanz = 1;
    // int length = arr.length;
    // while (distanz < length) {
    // distanz = 3 * distanz + 1;
    // }

    // while (distanz >= 1) {
    // int n = arr.length;
    int n = arr.length;

    for (int gap = n / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < n; i++) {
        int key = arr[i];
        int j = i;
        while (j >= gap && arr[j - gap] > key) {
          arr[j] = arr[j - gap];
          j -= gap;
        }
        arr[j] = key;
      }
    }
    return arr;
  }
}
