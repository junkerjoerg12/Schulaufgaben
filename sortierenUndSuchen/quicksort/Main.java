package quicksort;

import java.util.Random;

public class Main {
  public static void main(String[] args) {
    // int length = Integer.MIN_VALUE;
    int[] arr = new int[1000000];
    Random rand = new Random();

    for (int i = 0; i < arr.length; i++) {
      arr[i] = rand.nextInt(100);
    }
    // printArr(arr);
    long start = System.currentTimeMillis();
    arr = sort(arr, 0, arr.length - 1);
    // printArr(arr);
    System.out.println("in nur " + (System.currentTimeMillis() - start) + "millis sortiert");
  }

  private static int[] sort(int[] arr, int start, int end) {
    if (end - start <= 1) {
      return arr;
    } else {
      int pivot = arr[end];
      int j = start;
      int i = j - 1;
      while (j <= end) {
        if (arr[j] < pivot) {
          i++;
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
        }
        j++;
      }
      i++;
      arr[end] = arr[i];
      arr[i] = pivot;
      sort(arr, start, i - 1);
      sort(arr, i + 1, end);
    }
    return arr;
  }

  private static void printArr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(arr[i]);
    }
  }
}