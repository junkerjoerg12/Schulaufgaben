package sortierenUndSuchen.insertionSort;

import java.util.Random;

public class insertionSort {

  public static void main(String[] args) {
    int[] arr = new int[100000000];
    Random random = new Random();
    for (int i = 0; i < arr.length; i++) {
      arr[i] = random.nextInt(1000000);
    }
    long start = System.currentTimeMillis();
    System.out.println("sortieren");
    arr = sortieren(arr);
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
    System.out.println(System.currentTimeMillis() - start);
  }

  private static int[] sortieren(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      int j = i - 1;
      int temp = arr[i];
      while (j >= 0 && arr[j] > temp) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = temp;
    }
    return arr;
  }
}