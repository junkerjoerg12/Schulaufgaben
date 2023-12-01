package binareSuche;

public class Main {
  public static void main(String[] args) {
    int[] zahlen = { 5, 7, 8, 15, 17, 18, 25, 100, 102, 145 };
    System.out.println(zahlen.length);
    System.out.println(suchebinaer(zahlen, 15, 0, zahlen.length));

  }

  public static int suchebinaer(int[] array, int gesucht, int untergrenze, int obergrenze) {
    int arraylength = obergrenze - untergrenze;
    if (array[arraylength / 2] > gesucht) {
      System.out.println("kleiner");
      return suchebinaer(array, gesucht, untergrenze, (int) (arraylength / 2));
    } else if (array[arraylength / 2] < gesucht) {
      System.out.println("groesser");
      return suchebinaer(array, gesucht, (int) (arraylength / 2), obergrenze);
    } else if ((array[arraylength / 2]) == gesucht) {
      return arraylength / 2;
    }
    return -1;

  }

}
